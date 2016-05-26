package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

import java.util.Date;

/**
 * Created by kilosakeyrocker on 26/05/16.
 */
public class IntermediateActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    ActorRef recepteur, errorController;

    public IntermediateActor(ActorRef recepteur, ActorRef errorController) {
        log.info("IntermediateActor constructor");
        this.recepteur = recepteur;
        this.errorController = errorController;
    }


    public void onReceive(Object msg) throws Exception {
        if(msg instanceof String) {
            log.info("Received message and sent to error controller");
            Message message = new Message();
            message.message = (String)msg;
            message.destination = recepteur;
            errorController.tell(message,null);
        }
    }
}
