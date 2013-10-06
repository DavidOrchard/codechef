// counting sort
object Main {
  def main(args: Array[String]) {
    val maxSize = 1000000
    val nums = Array.fill[Int](maxSize+1)(0)
    // tradeoff a bit of run time checking per loop to save iterating over the whole nums array later
    // they may have skewed the #s to one end or the other
    var min = maxSize
    var max = -1
    
    // would be nice to be able to do getLines.take(1).toList(0) then not have to check in the for loop
    // Need getLines for fastest perf, faster than Scanner
    var t = -1
    for(line <- io.Source.stdin.getLines) {
      if( t == -1) {
        t = line.toInt
      } else {
        val num = line.toInt
        nums(num) += 1
        if( num > max) max = num
        if( num < min) min = num
      }      
    }
    for(i <- min to max) {
      if(nums(i) > 0 ) {
        println(i)
      }
    }
  }
}
