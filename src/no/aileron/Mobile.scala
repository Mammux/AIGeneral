package no.aileron

import no.aileron.Direction._

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

class Loner(map: Map) extends Mobile(map: Map) {
  code = new If(Alone,
      Nop, 
      new If(new Room(East), new Go(East), 
          new If(new Room(West), new Go(West),
              new If(new Room(North), new Go(North),
                  new If(new Room(South), new Go(South),
                      new Go(Random))
              )
          )
      )
  )
  map.addMobile(this)
  
  override val name = "Loner"
  override val letter = 'L'
}
