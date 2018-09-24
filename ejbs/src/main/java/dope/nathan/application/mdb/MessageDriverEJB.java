package dope.nathan.application.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
        name = "MessageHandler",
        activationConfig = {
                @ActivationConfigProperty( propertyName = "destinationType",
                        propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty( propertyName = "destinationLookup",
                        propertyValue ="java:/jms/queue/ExpiryQueue")
        }
)
public class MessageDriverEJB implements MessageListener {
//      @Resource
//    private MessageDrivenContext mdctx;

//      @EJB
//    ClientManagerEJBLocal clientManagerEJBLocal;

    public MessageDriverEJB(){
    }

    public void onMessage(Message message) {
        String messageStr = null;
        try {
            messageStr = ((TextMessage) message).getText();
            System.out.println("Got a message form ExpiryQueue: " + messageStr + "\"");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
