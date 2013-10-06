// Counting sort with readline processing of stdin
// output and input processing commented out to see if this can finish in <5 seconds.
process.stdin.resume();
process.stdin.setEncoding('utf8');

var arr = new Buffer(1000001);
var n;
var min = 1000001;
var max = 0;
var thisIteration = 0;
var linesProcessed = 0;
var resultsDisplayed = false;

var start = process.hrtime();

var elapsed_time = function(note){
    var precision = 3; // 3 decimal places
    var elapsed = process.hrtime(start)[1] / 1000000; // divide by a million to get nano to milli
    console.log(elapsed.toFixed(precision) + " ms - " + note); // print message + time
    start = process.hrtime(); // reset the timer
}

var readline = require('readline');

var rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
  terminal:false
});

function displayResults() {
  for(var i = min; i <= max; i++) {
     if( arr[i]) {
       while(arr[i]--) {
//         process.stdout.write( i + "\n");
         }    
     }
   }
//   elapsed_time("Execution");
  
}

rl.on('line', function(line) {
/*  var num = +line;
  if(!n) {
    n = num;
    return;
  }
 
  if( num > max ) {
    max = num;
  }
  if( num < min) {
    min = num;
  }
  if(arr[num]) {
    arr[num]++;
  } else {
    arr[num] = 1;
  }
  
  thisIteration++;
*/
});

process.stdin.on('end', function() {
    displayResults();
 
//  var util = require('util');
//  console.log(util.inspect(process.memoryUsage()));
});
  
