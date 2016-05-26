package m2dl.pcr.akka.stringservices;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by kilosakeyrocker on 26/05/16.
 */
public class Recepteur extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public Recepteur() {
        log.info("Recepteur constructor");
    }

    @Override
    public void onReceive(Object msg) throws Exception {

        if (msg instanceof String) {
            log.info("Message received>> " + msg + "!");
        } else {
            unhandled(msg);
        }
    }
}
