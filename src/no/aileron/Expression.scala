package no.aileron

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




