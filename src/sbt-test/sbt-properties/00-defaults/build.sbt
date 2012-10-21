
organization := "name.heikoseeberger.sbt.properties"

name := "sbt-properties-test"

version := "1.2.3"

seq(propertiesSettings: _*)

TaskKey[Unit]("verify-file") <<= PropertiesKeys.file map { file =>
  val userHome = new File(System.getProperty("user.home"), ".sbt-properties")
  if (file != userHome)
    error("Expected file to be %s, but was %s!".format(userHome, file))
}
