package dope.nathan.application.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(
        name = "MessageHandlerFromScheduler",
        activationConfig = {
                @ActivationConfigProperty( propertyName = "destinationType",
                        propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty( propertyName = "destinationLookup",
                        propertyValue ="java:/jms/queue/ExpiryQueue")
        }
)
public class MessageFromSchedulerMDB implements MessageListener {

    public MessageFromSchedulerMDB(){
    }

    public void onMessage(Message message) {
        String messageStr = null;
        try {
            messageStr = ((TextMessage) message).getText();
            System.out.println("!!!!!!!!!!!! GOT A MESSAGE: " + messageStr);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
