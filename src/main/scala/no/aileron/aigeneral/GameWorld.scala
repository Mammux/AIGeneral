package no.aileron.aigeneral

import javax.script.ScriptEngineManager
import akka.actor.ActorSystem
import akka.actor.Props

object GameWorld extends App {
	override def main(args: Array[String]) {
				/*		
		val sem = new ScriptEngineManager
		val map = new Map
		for (i <- 1 to 30)
			new Loner(map)
				new LuaMobile(map, sem, "print (world:room(east), world:room(west))")
		new LuaMobile(map, sem, "if not world:alone() then mobile:goEast() end")
		new LuaMobile(map, sem, "if not world:alone() then mobile:goEast() end")
		for (i <- 1 to 3) {
			map.tick
		}
		map.dump
				 */
	  
	  val system = ActorSystem("aigeneral")
	  val mapActor = system.actorOf(Props(classOf[MapActor]), "map")
	  val dumbActor = system.actorOf(Props(classOf[DumbMobile]), "dumb")
	  dumbActor.tell(Activated, mapActor)
 
	}
}