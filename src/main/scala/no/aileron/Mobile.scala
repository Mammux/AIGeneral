package no.aileron

import no.aileron.Direction._
import javax.script.ScriptEngineManager

abstract class Mobile(m: Map) {
  var x = 0
  var y = 0
  var code: Code = Nop
  val map = m
  
  def execute = {code.execute(this)}
  def name: String
  def letter: Char
  
  def goEast = {x = x + 1}
}

class LuaMobile(m: Map, sem: ScriptEngineManager, script: String) extends Mobile(m: Map) {
  code = new LuaCode(sem, script)
  map.addMobile(this)
  override val name = "Lua"
  override val letter = 'U'
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

// LONER
//
// If there is room adjacent to him he moves there, otherwise he moves in a random direction

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
