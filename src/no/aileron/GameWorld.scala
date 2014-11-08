package no.aileron

object GameWorld extends App {
	override def main(args: Array[String]) {
		val map = new Map
		for (i <- 1 to 30)
			new Loner(map)
		for (i <- 1 to 5) {
			map.tick
		}
		map.dump
	}
}