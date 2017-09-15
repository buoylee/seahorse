import com.typesafe.sbt.packager.docker.Cmd

dockerBaseImage := "anapsix/alpine-java:jre8"
dockerExposedPorts := Seq(8080)
dockerUpdateLatest := true
dockerCommands ++= Seq(
  Cmd("USER", "root")
)
