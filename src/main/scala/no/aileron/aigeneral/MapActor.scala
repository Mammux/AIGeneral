package no.aileron.aigeneral

import java.util.ArrayList
import scala.collection.JavaConversions._
import akka.actor.Actor
import akka.event.Logging
import akka.actor.ActorLogging
import akka.persistence.EventsourcedProcessor
import Direction.Direction

// Incoming msgs to map

case object GetState
case object ThrowUp

case class AddMobile(mob: Mobile)
case class Move(id: Long, dir: Direction)

// Outgoing msgs from map

case class Added(id: Long)
case class Moved(x : Int, y: Int)

class MapActor extends EventsourcedProcessor with ActorLogging {
  
  var state = new Map
  
  def receiveRecover = {
    case map: Map => {
      log.info(s"Recovered map $map")
      state = map
    }
    case o => log.info(s"received unknown message for recover: $o")
  }
  
  def receiveCommand = {
    case GetState => sender ! state
    case ThrowUp => throw new Exception("Just testing exceptions")
    case AddMobile(mob) => { 
      log.info(s"adding mobile $mob")
      state.addMobile(mob)
      sender ! Added(0)
    }
    case "test" => log.info("received test")
    case o => log.info(s"received unknown message for recover: $o")
  }
  
}