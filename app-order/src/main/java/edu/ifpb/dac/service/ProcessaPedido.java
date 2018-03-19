package edu.ifpb.dac.service;

import edu.ifpb.dac.entidade.Pedido;
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
    
    @Resource("jms/Pedidos")
    private Topic topic;
    
    @Inject
    private JMSContext context;
    
    public void enviarParaProcessamento(Pedido pedido) {
        JMSProducer producer = context.createProducer();
        producer.send(topic, pedido);
    }
}
