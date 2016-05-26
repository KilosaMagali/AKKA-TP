package m2dl.pcr.akka.stringservices;

import akka.actor.ActorRef;

/**
 * Created by kilosakeyrocker on 26/05/16.
 */
public class Message {
    public  String message;
    public ActorRef destination;

    @Override
    public String toString(){
        return "Message: " + message + "\nDestination: " + destination;
    }
}
