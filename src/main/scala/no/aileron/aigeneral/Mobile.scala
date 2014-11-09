package no.aileron.aigeneral

import Direction._
import javax.script.ScriptEngineManager
import akka.actor.ActorRef
import akka.actor.ActorLogging
import akka.persistence.EventsourcedProcessor
import scala.NotImplementedError

case object Activated

trait Mobile {
  var x: Int 
  var y: Int
  
  val name: String
  val letter: Char
}

abstract class MobileActor extends EventsourcedProcessor with ActorLogging with Mobile {
  var x = 0
  var y = 0
  var id: Long = 0
    
  def receiveRecover = {
    
    case Added(myId: Long) => {
      log.info("recover Added")
      id = myId
    }
    
    case Moved(x: Int, y: Int) => {
      log.info("recover Moved")
      this.x = x
      this.y = y 
    }
    
    case Activated => {
      log.info("recover Activated")
      sender ! AddMobile(this)
    }
    
    case o: Object => log.info(s"received unknown recover message: $o")
  }
  
  def receiveCommand = {
    
    case Added(myId: Long) => {
      log.info("Added")
      id = myId
    }
    
    case Moved(x: Int, y: Int) => {
      log.info("Moved")
      this.x = x
      this.y = y 
    }
    
    case Activated => {
      log.info("Activated")
      sender ! AddMobile(this)
    }
    
    case o: Object => log.info(s"received unknown message: $o")
  }
}

/*
class LuaMobile(m: MapActor, sem: ScriptEngineManager, script: String) extends Mobile(m: MapActor) {
  code = new LuaCode(sem, script)
  map ! addMobile(this)
  override val name = "Lua"
  override val letter = 'U'
}
*/

class DumbMobile extends MobileActor {
  override val name = "Dumb"
  override val letter = 'D'
}