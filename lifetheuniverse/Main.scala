import scala.util.control.Breaks._
object Main {
  def main(args: Array[String]) {
    var ok = true;
    def iterateTil42 {
      io.Source.stdin.getLines.foreach { ln => 
        if(ln == "42") {
          break()
        } else {
          println(ln)
        }
      }
    }
    iterateTil42
  }
}