package m2dl.pcr.akka.cribleeratosthene;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kilosakeyrocker on 26/05/16.
 */
public class System {
    public static final Logger log = LoggerFactory.getLogger(m2dl.pcr.akka.cribleeratosthene.System.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        //Thread.sleep(5000);

        final ActorRef actorRef = actorSystem.actorOf(Props.create(m2dl.pcr.akka.cribleeratosthene.Crible.class), "crible-actor");

        for(int i = 2; i < 20; i++) {
            actorRef.tell(i, null);
            Thread.sleep(100);
        }
        actorRef.tell(-1,null);


        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }
}
