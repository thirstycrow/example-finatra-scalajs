import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object lib {

  implicit object v extends Version

  trait Version {
    val finagle = "6.30.0"
    val finagleKafka = "0.2.0"
    val finatra = "2.1.1"
    val scalarx = "0.2.8"
    val scalatags = "0.5.3"
    val scrooge = "4.1.0"
    val thrift = "0.8.0"
  }

  object finagle {
    def mysql    (implicit v: Version) = "com.twitter" %% "finagle-mysql"     % v.finagle
    def redis    (implicit v: Version) = "com.twitter" %% "finagle-redis"     % v.finagle
    def thrift   (implicit v: Version) = "com.twitter" %% "finagle-thrift"    % v.finagle
    def thriftmux(implicit v: Version) = "com.twitter" %% "finagle-thriftmux" % v.finagle
    def http     (implicit v: Version) = "com.twitter" %% "finagle-http"      % v.finagle

    def kafka(implicit v: Version)     = "com.github.okapies" %% "finagle-kafka" % v.finagleKafka
  }

  object finatra {
    def http   (implicit v: Version) = "com.twitter.finatra" %% "finatra-http"    % v.finatra
    def jackson(implicit v: Version) = "com.twitter.finatra" %% "finatra-jackson" % v.finatra
    def slf4j  (implicit v: Version) = "com.twitter.finatra" %% "finatra-slf4j"   % v.finatra
    def thrift (implicit v: Version) = "com.twitter.finatra" %% "finatra-thrift"  % v.finatra
  }

  object scrooge {
    def core(implicit v: Version) = "com.twitter" %% "scrooge-core" % v.scrooge
  }

  //def scalarx(implicit v: Version) = "com.lihaoyi" %%% "scalarx" % v.scalarx

  //def scalatags(implicit v: Version) = "com.lihaoyi" %%% "scalatags" % v.scalatags

  def thrift(implicit v: Version) = "org.apache.thrift" % "libthrift" % v.thrift
}
