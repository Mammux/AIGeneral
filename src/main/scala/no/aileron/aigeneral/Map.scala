package no.aileron.aigeneral

import java.util.ArrayList
import scala.collection.JavaConversions._
import akka.actor.Actor
import akka.event.Logging
import akka.actor.ActorLogging
import akka.persistence.EventsourcedProcessor


class Map {
	var minX = -5
	var maxX = 5
	var minY = -5
	var maxY = 5
	var origoX = 0
	var origoY = 0
  
	var mobiles = List[Mobile]()
	
	def addMobile(mobile: Mobile) {
	  mobiles = mobile :: mobiles
	  mobile.x = origoX
	  mobile.y = origoY
	}
	
	def removeMobile(mobile: Mobile) {
	  mobiles = mobiles diff List(mobile)
	}
	
	def dump {
	  var canvas = ""
	  for (y <- minY to maxY) {
	    for (x <- minX to maxX) {
	      mobiles.filter(m => m.x == x && m.y == y) match {
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

