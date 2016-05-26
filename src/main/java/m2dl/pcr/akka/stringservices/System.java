package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kilosakeyrocker on 26/05/16.
 */
public class System {
    public static final Logger log = LoggerFactory.getLogger(m2dl.pcr.akka.exo1_2.System.class);

    public static void main(String... args) throws Exception {

        final ActorSystem actorSystem = ActorSystem.create("actor-system");

        //Thread.sleep(5000);

        final ActorRef cryptageRef = actorSystem.actorOf(Props.create(m2dl.pcr.akka.stringservices.CryptageProvider.class), "cryptage-actor");
        final ActorRef errorControlRef = actorSystem.actorOf(Props.create(m2dl.pcr.akka.stringservices.ErreurControleProvider.class), "errorcontrol-actor");
        final ActorRef recepteurRef = actorSystem.actorOf(Props.create(m2dl.pcr.akka.stringservices.Recepteur.class), "recepteur-actor");



        Message message = new Message();
        message.message = "this is a message";
        message.destination = recepteurRef;

        /*cryptageRef.tell(message,null);

        errorControlRef.tell(message,null);*/

        //******************

        final ActorRef intermediateRef = actorSystem.actorOf(Props.create(m2dl.pcr.akka.stringservices.IntermediateActor.class,recepteurRef,errorControlRef), "intermediate-actor");
        message.destination = intermediateRef;
        cryptageRef.tell(message,null);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }
}
