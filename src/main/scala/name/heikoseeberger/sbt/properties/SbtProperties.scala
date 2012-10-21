/*
 * Copyright 2011 Heiko Seeberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package name.heikoseeberger.sbt.properties

import java.io.FileInputStream
import java.util.Properties
import sbt._
import sbt.Keys._
import scala.collection.JavaConverters._

object SbtProperties extends Plugin {

  def propertiesSettings: Seq[Setting[_]] = {
    import PropertiesKeys._
    List(
      properties <<= file(initializeProperties),
      file := defaultFile
    )
  }

  private def initializeProperties(file: File) = {
    if (file.exists()) {
      //      streams.log.warn("""Reading properties from "%s".""" format file)
      val in = new FileInputStream(file)
      try {
        val properties = new Properties
        properties.load(in)
        properties.asScala.toMap
      } finally if (in != null) in.close()
    } else {
      //      streams.log.warn("""Properties file "%s" does not exist!""" format file)
      Map[String, String]()
    }
  }

  private def defaultFile = new File(System.getProperty("user.home"), ".sbt-properties")

  object PropertiesKeys {

    val properties: SettingKey[Map[String, String]] = SettingKey[Map[String, String]](
      prefix("properties"),
      "Properties made available as a map."
    )

    val file: SettingKey[File] = SettingKey[File](
      prefix("file"),
      "File to read properties from."
    )

    private def prefix(key: String) = "properties-" + key
  }
}
