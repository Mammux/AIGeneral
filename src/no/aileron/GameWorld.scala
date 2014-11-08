package no.aileron

object GameWorld extends App {
	override def main(args: Array[String]) {
		val map = new Map
		new Scout(map)
		new Scout(map)
		new Scout(map)
		map.tick
		println("--")
		map.tick
		println("--")
		map.tick
	}
}