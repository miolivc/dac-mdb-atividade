/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.app.email;

import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author alexalins
 * @author miolivc
 */
@MessageDriven
public class EmailSender implements MessageListener{

    @Override
    public void onMessage(Message message) {
    
    }
    
}
