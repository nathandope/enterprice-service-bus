package dope.nathan.application.ejb;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Singleton
@Startup
public class Scheduler2EJB {

    @Inject
    @JMSConnectionFactory("java:/ConnectionFactory")
    private JMSContext context;

    @Resource(lookup = "java:/jms/queue/ExternalQueue")
    private Queue dataQueue;

    public Scheduler2EJB() {
    }

    @Schedule(
            minute = "*/1",
            hour = "*",
            info = "1MinScheduler2",
            persistent = false
    )
    public void sendMessage() {
        context.createProducer().send(dataQueue, "This is message from ExternalQueue");
    }
}
