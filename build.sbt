name := "AIGeneral"

version := "1.0"

scalaVersion := "2.10.4"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

resolvers += "IBiblio mirror" at "http://mirrors.ibiblio.org/maven2/"

libraryDependencies +=
  "com.typesafe.akka" %% "akka-actor" % "2.4-SNAPSHOT"
  
libraryDependencies +=
  "com.typesafe.akka" %% "akka-testkit" % "2.4-SNAPSHOT"
  
libraryDependencies +=
  "com.typesafe.akka" %% "akka-persistence-experimental" % "2.4-SNAPSHOT"
  
libraryDependencies +=
  "org.luaj" % "luaj-jse" % "3.0"
  
libraryDependencies += "org.specs2" %% "specs2" % "1.14" % "test"