// scala 2.13.4
import $ivy.`org.plotly-scala::plotly-render:0.8.0`
import $ivy.`com.github.pathikrit::better-files:3.9.1`

import better.files._
import plotly._, element._, layout._, Plotly._
import scala.util.Random
import scala.sys.process._

@main
def main() = {
  val labels  = Seq("Banana", "Banano", "Grapefruit")
  val valuesA = labels.map(_ => Random.nextGaussian())
  val valuesB = labels.map(_ => 0.5 + Random.nextGaussian())
  Seq(
    Bar(labels, valuesA, name = "A"),
    Bar(labels, valuesB, name = "B")
  ).plot(
    useCdn = false,
    title = "Level"
  )
}

@main
def processUsage() = {
  val cmd     = "python -u test.py"
  val process = Process(cmd)
  val running = process.run {
    new ProcessLogger {
      override def out(s: => String): Unit = println(s"println $s from scala")
      override def buffer[T](f: => T): T   = ???
      override def err(s: => String): Unit = println(s"print err $s from scala")
    }
  }
  sys.addShutdownHook {
    println("Shutting down application...")
    println(s"Process finished in shutdown hook with code ${running.exitValue()}")
  }
}
