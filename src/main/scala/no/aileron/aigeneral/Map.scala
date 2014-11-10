package no.aileron.aigeneral

import java.util.ArrayList
import scala.collection.JavaConversions._
import akka.actor.Actor
import akka.event.Logging
import akka.actor.ActorLogging
import akka.persistence.EventsourcedProcessor
import akka.actor.ActorRef


case class MobileData(letter: Char, x: Int, y: Int, actor: ActorRef)

class Map {
	var minX = -5
	var maxX = 5
	var minY = -5
	var maxY = 5
	var origoX = 0
	var origoY = 0
  
	var mobiles = Map[Long,MobileData]()
	var nextId: Long = 0
	
	def addMobile(mobile: MobileData): Long = {
	  val id = nextId
	  mobiles = mobiles + (id -> mobile)
	  nextId = nextId + 1
	  return id
	}
	
	def removeMobile(id: Long) {
	  mobiles = mobiles - id
	}
	
	def dump {
	  var canvas = ""
	  for (y <- minY to maxY) {
	    for (x <- minX to maxX) {
	      mobiles.values.filter(m => m.x == x && m.y == y) match {
	        case m :: Nil => canvas = canvas + m.letter
	        case m :: _ => canvas = canvas + "*"
	        case Nil => canvas = canvas + '.'
	      }
	    }
	    canvas = canvas + "\n"
	  }
	  println(canvas)
	}
}

