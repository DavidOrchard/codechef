object Main {
  def main(args: Array[String]) {
    val line = readLine().split(" ")
    val (n, k) = (line(0).toInt, line(1).toInt)
    var count = 0
    for( i <- 1 to n ) {
      var ln = readLine()
      if(ln.toInt % k == 0) count += 1  
    }
    println(count)
  }
}