import $ivy.`com.github.pathikrit::better-files:3.9.1`
import better.files._
@main
def main()= {
  val f = File("./test.sc")
  f.write("""println("hello world")""")
  f.delete()
}