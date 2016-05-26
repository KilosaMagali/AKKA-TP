package m2dl.pcr.akka.stringservices;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by kilosakeyrocker on 26/05/16.
 */
public class CryptageProvider extends UntypedActor {
    StringUtils stringUtils;
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public CryptageProvider() {
        stringUtils  = new StringUtils();
        log.info("CryptageProvider constructor");
    }
    public void onReceive(Object msg) throws Exception {
        if(msg instanceof Message) {
            log.info("Crypting the message : " + ((Message)msg));
            String cryptedMsg = stringUtils.crypte(((Message)msg).message);
            ((Message)msg).destination.tell(cryptedMsg,null);
        }

    }
}
