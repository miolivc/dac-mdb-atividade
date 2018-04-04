package edu.ifpb.dac.app.email;

import edu.ifpb.dac.entidade.Pedido;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

/**
 * @author alexalins
 * @author miolivc
 */
@MessageDriven(
    mappedName = "jms/Pedidos",
    activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType",
            propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destinationName",
            propertyValue = "topic")
    }
)
public class EmailSender implements MessageListener{

    private static final String EMAIL = "dac-mdb-atividade@gmail.com";
    private static final String SENHA = "dac-mdb-123";
    
    @Override
    public void onMessage(Message message) {
        try {
            Pedido pedido = message.getBody(Pedido.class);
            boolean[] typeEmail = new boolean[1];
            
            if (message.propertyExists("typeEmail")) {
            
                if (message.getStringProperty("typeEmail").equals("messageProcess")) {
                    enviarEmailPedido(pedido, typeEmail);
                    
                } else if (message.getStringProperty("typeEmail").equals("confirmacao")) {
                
                    if (message.propertyExists("confirmacao")) {
                        if (message.getBooleanProperty("confirmacao")) {
                            typeEmail[0] = true;
                            enviarEmailPedido(pedido, typeEmail);
                        } else {
                            typeEmail[0] = false;
                            enviarEmailPedido(pedido, typeEmail);
                        }
                    }
                    
                }
            }
        } catch (JMSException ex) {
            Logger.getLogger(EmailSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void enviarEmailPedido(Pedido pedido, boolean[] args) {
        try {
            // Setting sender email properties 
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setAuthenticator(new DefaultAuthenticator(EMAIL, SENHA));
//            email.setSmtpPort(587);
            email.setTLS(true);
            email.setSSL(true);
            email.setFrom(EMAIL);
            
            String clientEmail = pedido.getCliente().getEmail();
            email.addTo(clientEmail);
            
            if (args.length == 0) {
                email.setSubject(String.format("NOVO PEDIDO EM PROCESSAMENTO", pedido.getId()));
            }
            
            if (args[0]) {
                email.setSubject(String.format("PEDIDO CONFIRMADO: %d", pedido.getId()));
            } else {
                email.setSubject(String.format("PEDIDO CANCELADO: %d", pedido.getId()));
            }
            
            email.setMsg(pedido.toString());
            email.send();
        } catch (EmailException ex) {
            System.err.println("Erro ao mandar email: " + ex.getLocalizedMessage());
        }
    }
}
