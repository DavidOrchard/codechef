object Main {
  
  def main(args: Array[String]) {
    val t = readLine().toInt
    var memo = Array.fill[BigInt](101)(0)
    memo(1) = 1
    
    def allFactorials(t: Int, memo: Array[BigInt]) {
    
      def factorial(n: Int):BigInt = {
         if( memo(n) == 0 && n != 1) {
          memo(n) = n * factorial (n - 1)
        }
        memo(n)
      }
      
      for( i <- 1 to t ) {
        println(factorial(readLine().toInt))
      }
    }
    allFactorials(t, memo)
  }  
}