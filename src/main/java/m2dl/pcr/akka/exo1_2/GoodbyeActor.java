package m2dl.pcr.akka.exo1_2;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by kilosakeyrocker on 26/05/16.
 */
public class GoodbyeActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public GoodbyeActor() {
        log.info("GoodbyeActor constructor");
    }

    @Override
    public void onReceive(Object msg) throws Exception {

        if (msg instanceof String) {
            log.info("Good bye " + msg + "!");
        } else {
            unhandled(msg);
        }
    }
}
