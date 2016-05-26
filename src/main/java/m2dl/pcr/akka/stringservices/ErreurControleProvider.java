package m2dl.pcr.akka.stringservices;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import m2dl.pcr.akka.cribleeratosthene.System;

import java.util.Date;

/**
 * Created by kilosakeyrocker on 26/05/16.
 */
public class ErreurControleProvider extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public ErreurControleProvider(){
        log.info("ErreurControleProvider constructor");
    }
    public void onReceive(Object msg) throws Exception {
        if(msg instanceof Message) {
            log.info("Controlled errors for : " + ((Message) msg).message);
            ((Message) msg).message = (Message) msg +" errors controlled at " + (new Date());
            ((Message)msg).destination.tell(((Message) msg).message,null);
        }

    }
}
