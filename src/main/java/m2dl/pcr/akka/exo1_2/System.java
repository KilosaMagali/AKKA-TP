package m2dl.pcr.akka.exo1_2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import m2dl.pcr.akka.helloworld2.*;
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

        final ActorRef actorRef = actorSystem.actorOf(Props.create(m2dl.pcr.akka.exo1_2.ParentActor.class), "parent-actor");

        actorRef.tell("John",null);
        actorRef.tell("Pauline",null);
        actorRef.tell("Eva",null);
        actorRef.tell("Bill",null);
        actorRef.tell("Marc",null);

        Thread.sleep(1000);

        log.debug("Actor System Shutdown Starting...");

        actorSystem.terminate();
    }
}
