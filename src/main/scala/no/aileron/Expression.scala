package no.aileron

import no.aileron.Direction._

// EXPRESSIONS
//
// Used inside code, always evaluate to true or false

abstract class Expression {
  def result(mobile: Mobile): Boolean
}

// Is the mobile alone in its space?

object Alone extends Expression {
  override def result(mobile: Mobile): Boolean = {
	  return mobile.map.mobiles.filter(m => m.x == mobile.x && m.y == mobile.y && m.ne(mobile)).isEmpty
  }
}

// Is the mobile at home (the starting space at the middle of the board)

object Home extends Expression {
  override def result(mobile: Mobile): Boolean = {
	  return (mobile.x == mobile.map.origoX && mobile.y == mobile.map.origoY)
  }
}

// Is there room one space away in the specified direction, in other words is that space empty?

class Room(dir: Direction) extends Expression {
  override def result(mobile: Mobile): Boolean = {
		var x = mobile.x
		var y = mobile.y
		
		dir match {
		  case North => y = y + 1
		  case South => y = y - 1
		  case East => x = x + 1
		  case West => x = x - 1
		  case Random => return false
		}
		
		if (x < mobile.map.minX || x > mobile.map.maxX || y < mobile.map.minY || y > mobile.map.maxY) return false
		
		return mobile.map.mobiles.filter(m => m.x == x && m.y == y && m.ne(mobile)).isEmpty
  }
}

// Basic boolean logic expressions 

class And(first: Expression, second: Expression) extends Expression {
  override def result(mobile: Mobile): Boolean = first.result(mobile) && second.result(mobile)
}

class Or(first: Expression, second: Expression) extends Expression {
  override def result(mobile: Mobile): Boolean = first.result(mobile) || second.result(mobile)
}

class Not(first: Expression) extends Expression {
  override def result(mobile: Mobile): Boolean = ! first.result(mobile)
}

// SCRIPT expressions

class ScriptWorld(mobile: Mobile) {
  def alone = Alone.result(mobile)
  def home = Home.result(mobile)
  def room(dir: Direction) = new Room(dir).result(mobile)
}


