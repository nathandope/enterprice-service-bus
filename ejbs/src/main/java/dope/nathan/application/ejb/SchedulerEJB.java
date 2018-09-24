package dope.nathan.application.ejb;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.*;

@Singleton
@Startup
public class SchedulerEJB {

    @Inject
    @JMSConnectionFactory("java:/ConnectionFactory")
    private JMSContext context;

    @Resource(lookup = "java:/jms/queue/ExpiryQueue")
    private Queue dataQueue;

    public SchedulerEJB() {
    }

    @Schedule(
            minute = "*/1",
            hour = "*",
            info = "1MinScheduler1",
            persistent = false
    )
    public void sendMessage() {
        context.createProducer().send(dataQueue, "This is message from ExpiryQueue");
    }
}
