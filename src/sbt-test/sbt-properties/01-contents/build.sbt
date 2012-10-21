
organization := "name.heikoseeberger.sbt.properties"

name := "sbtproperties-test"

version := "1.2.3"

seq(propertiesSettings: _*)

PropertiesKeys.file := new File(".sbt-properties")

TaskKey[Unit]("verify-contents") <<= PropertiesKeys.properties map { props =>
  if ((props get "foo.bar") != Some("baz"))
    error("Expected properties to contain foo.bar -> baz!")
}
