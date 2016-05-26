package m2dl.pcr.akka.cribleeratosthene;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;
import m2dl.pcr.akka.exo1_2.HelloActor;

/**
 * Created by kilosakeyrocker on 26/05/16.
 */
public class Crible extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private int id;
    private boolean hasFirst = false;
    private ActorRef nextActorRef;

    public Crible() {
        log.info("Crible constructor");
    }



    Procedure<Object> trailling = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Integer) {
                if(!hasFirst) {
                    id = (Integer) msg;
                    hasFirst = true;
                    log.info("PRIME NUMBER FOUND " + id);
                }
                else {
                    if(((Integer)msg % id) != 0) {
                        nextActorRef = getContext().actorOf(Props.create(Crible.class), "crible-actor");
                        nextActorRef.tell(msg, getSelf());
                        getContext().become(intermediate,false);
                    }

                }


            } else {
                unhandled(msg);
            }
        }
    };

    Procedure<Object> intermediate = new Procedure<Object>() {
        public void apply(Object msg) throws Exception {
            if (msg instanceof Integer) {
                if(((Integer)msg % id) != 0) {
                    nextActorRef.tell(msg,getSelf());
                }
                else {

                    log.info("Crible <"+ id + "> Filtered " + (Integer)msg);
                }

            } else {
                unhandled(msg);
            }
        }
    };
    @Override
    public void postStop() {
        log.info("DESTRUCTED " + id);
    }



    public void onReceive(Object msg) throws Exception {
        if((Integer)msg == -1){
            //nextActorRef.tell(msg,getSelf());
            log.info("Destruction " + id);
            getContext().stop(getSelf());
        }
        else
         trailling.apply(msg);
    }
}
