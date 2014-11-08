package no.aileron

// CODES
//
// Stuff that mobiles can do

abstract class Code {
  def execute(mobile: Mobile) = {}
}

// Move the mobile

import no.aileron.Direction._

class Go (direction: Direction) extends Code {
	override def execute(mobile: Mobile) = {
		val d = if (direction == Random) {
		  Direction(scala.util.Random.nextInt(4))
		} else direction 
		if (d == North && mobile.y < mobile.map.maxY) mobile.y += 1
		if (d == South && mobile.y > mobile.map.minY) mobile.y -= 1
		if (d == East && mobile.x < mobile.map.maxX) mobile.x += 1
		if (d == West && mobile.x > mobile.map.minX) mobile.x -= 1
	}
}

// Evaluate the expression and either do one thing or another

class If (ifExpr: Expression, thenCode: Code, elseCode: Code) extends Code {
	override def execute(mobile: Mobile) = {
	  if (ifExpr.result(mobile))
	    thenCode.execute(mobile)
	    else
	    elseCode.execute(mobile)
	}
}

// There is intentionally no "sequence" class here yet, 
// to crudely make sure mobiles can only do one action each tick

// Do nothing

object Nop extends Code

// Change the code in another mobile, with the specified name, in the same space

class Tell(name: String, order: Code) extends Code {
  override def execute(mobile: Mobile) = {
    mobile.map.mobiles.filter(m => m.x == mobile.x && m.y == mobile.y && m.ne(mobile) && m.name == name) match {
      case m :: _ => m.code = order 
      case Nil =>
    }
  }
}

// The mobile changes its own code

class Recode(program: Code) extends Code {
  override def execute(mobile: Mobile) = {
    mobile.code = program
  }
}

// Self-destruct

object Terminate extends Code {
  override def execute(mobile: Mobile) = {
    mobile.map.removeMobile(mobile)
  }
}

