package edu.ifpb.dac.service;

import edu.ifpb.dac.entidade.Pedido;
import java.util.Arrays;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;

/**
 *
 * @author alexalins
 * @author miolivc
 */
@Stateless
public class ProcessaPedido {
    
    @Resource(lookup = "jms/Pedidos")
    private Topic topic;
    
    @Inject
    private JMSContext context;
    
    public void enviarParaProcessamento(Pedido pedido) {
        JMSProducer producer = context.createProducer();
        producer.setProperty("destiny", "credit");
        producer.setProperty("typeEmail", "messageProcess");
        producer.send(topic, pedido);
    }
}
