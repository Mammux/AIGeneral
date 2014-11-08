package no.aileron

abstract class Mobile(m: Map) {
  var x = 0
  var y = 0
  var code: Code = Nop
  val map = m
  
  def execute = {code.execute(this)}
  def name: String
  def letter: Char
}

// SCOUT
//
// A very simple mobile that moves east until it's alone

class Scout(map: Map) extends Mobile(map: Map) {
  code = new If(Alone, Nop, new Go(Direction.East))
  map.addMobile(this)
  
  override val name = "Scout"
  override val letter = 'S'
}
