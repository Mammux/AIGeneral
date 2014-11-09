package no.aileron

import javax.script.ScriptEngineManager

object GameWorld extends App {
	override def main(args: Array[String]) {
	  val sem = new ScriptEngineManager
		val map = new Map
/*		
		for (i <- 1 to 30)
			new Loner(map)
*/		
		new LuaMobile(map, sem, "print (world:room(east), world:room(west))")
		new LuaMobile(map, sem, "if not world:alone() then mobile:goEast() end")
		new LuaMobile(map, sem, "if not world:alone() then mobile:goEast() end")
		for (i <- 1 to 3) {
			map.tick
		}
		map.dump
	}
}