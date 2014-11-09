package no.aileron.aigeneral

import akka.actor._
import akka.testkit.{ImplicitSender, TestKit}
import com.typesafe.config.{ConfigValueFactory, ConfigFactory}
import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import org.specs2.matcher.ThrownExpectations

class MapSpec extends Specification {
val config = ConfigFactory.load().withValue("akka.persistence.journal.plugin", ConfigValueFactory.fromAnyRef("akka.persistence.journal.inmem"))
 
  "MapActor" should {
    "Return a decent map" in new scope {
      mapActor ! "test"
 
      mapActor ! GetState
      expectMsgClass(classOf[Map])
    }
 
    "Recover previous state" in new scope {
      mapActor ! "test"
 
      // Tell actor to kill itself with exception
      mapActor ! ThrowUp
 
      // Get restored state
      mapActor ! GetState
      expectMsgClass(classOf[Map])
    }
  }
 
  class scope extends TestKit(ActorSystem("test", config)) with Scope with ThrownExpectations with ImplicitSender {
    val mapActor = system.actorOf(Props[MapActor])
  }
}