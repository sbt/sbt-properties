
organization := "name.heikoseeberger.sbtproperties"

name := "sbtproperties-test"

version := "1.2.3"

seq(propertiesSettings: _*)

TaskKey[Unit]("verify-file") <<= PropertiesKeys.file map { file =>
  val userHome = new File(System.getProperty("user.home"), ".sbtproperties")
  if (file != userHome)
    error("Expected file to be %s, but was %s!".format(userHome, file))
}
