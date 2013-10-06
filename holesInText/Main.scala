object Main {
  
  def main(args: Array[String]) {
    val t = readLine().toInt
    // ABCDEFGHIJKLMNOPQRSTUVWXYZ
    val holes: Array[Int] = Array(1,2,0,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0)
    
    def countWords(t: Int, holes: Array[Int]) {
    
      def countWord(word: String):Int = {
        def lookup(c: Char) = c match {
          case 'A' => 1
          case 'B' => 2
          case 'D' => 1
          case 'O' => 1
          case 'P' => 1
          case 'Q' => 1
          case 'R' => 1
          case _ => 0
        }
        var count = 0
        for ( c <- word.toCharArray() ) {
//          val offset = c - 'A'
//          count += holes(offset)
          count += lookup(c)
        }
        count
      }
      
      for( i <- 1 to t ) {
        println(countWord(readLine()))
      }
    }
    countWords(t, holes)
  }  
}