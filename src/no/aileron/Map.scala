package no.aileron

import java.util.ArrayList
import scala.collection.JavaConversions._

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
	
	def tick {
	  mobiles foreach {
	    m: Mobile => m.execute
	  }
	}
	
	def dump {
	  var canvas = ""
	  for (x <- minX to maxX) {
	    for (y <- minY to maxY) {
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