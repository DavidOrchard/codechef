object Main {
  def main(args: Array[String]) {
    val line = readLine().split(" ")
    var (amt, balance) = (line(0).toInt, line(1).toFloat)    
//    val amt, balance = io.Source.stdin.getLines.take(1).split(" ")
    if( (amt % 5) == 0 && balance -.5 >= amt) {
      balance -= ( amt + 0.5 ).floatValue
    }
    printf("%.2f\n", balance)
  }
}