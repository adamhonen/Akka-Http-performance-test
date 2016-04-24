name := "akka-http-performance-test"
organization := "com.example"
version := "1.0"
scalaVersion := "2.11.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.4.4"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaV
  )
}


mainClass in(Compile, run) := Some("com.example.rest.WebServer")

assemblyJarName in assembly := "http.jar"

cancelable in Global := true

