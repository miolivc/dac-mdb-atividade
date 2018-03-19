/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.app.credit.card;

import edu.ifpb.dac.entidade.Pedido;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;

/**
 *
 * @author alexalins
 * @author miolivc
 */

@MessageDriven(
        mappedName = "java:global/jms/Pedidos",
        activationConfig ={
            @ActivationConfigProperty(propertyName = "destinationType",
                    propertyValue = "javax.jms.Topic"),
            @ActivationConfigProperty(propertyName = "destinationName",
                    propertyValue = "topic"),
            @ActivationConfigProperty(propertyName = "messageSelector",
                    propertyValue = "destiny = 'credit'")
        })

public class ProcessCreditCard implements MessageListener {

    @Resource(lookup = "jms/Pedidos")
    private Topic topic;
    @Inject
    private JMSContext context;
    
    private BigDecimal limiteCartao = new BigDecimal(500);
    
    @Override
    public void onMessage(Message message) {
        try {
            JMSProducer producer = context.createProducer();
                    
            Pedido pedido = message.getBody(Pedido.class);
            if (pedido.valorPedido().compareTo(limiteCartao) == -1) {
                // Mensagem de erro
                producer.setProperty("confirmacao", true);
            } else {
                // Mensagem de sucesso
                producer.setProperty("confirmacao", false);
            }
            producer.setProperty("typeEmail", "confirmacao");
            producer.send(topic, pedido);
        } catch (JMSException ex) {
            Logger.getLogger(ProcessCreditCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
