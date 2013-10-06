// Counting sort with chunked processing of stdin
// output commented out to check codechef timing, to get <5 s
// some input processing takes ~120ms on my machine, ~1.5s on cc
// processing each chunk takes ~400ms/~3.86s
// looping over array takes ~423/4.42s
// outputing data takes 
process.stdin.resume();
process.stdin.setEncoding('utf8');
var line0;
var arr = [];
var lastLine = '';
var n;
var min = 1000001;
var max = 0;
var nThisIteration = 0;
var linesProcessed = 0;

var start = process.hrtime();

var elapsed_time = function(note){
    var precision = 3; // 3 decimal places
    var elapsed = process.hrtime(start)[1] / 1000000; // divide by a million to get nano to milli
    console.log(elapsed.toFixed(precision) + " ms - " + note); // print message + time
    start = process.hrtime(); // reset the timer
}

process.stdin.on('data', function(chunk) {
  var lines = chunk.split('\n');
  if(!n) {
    n = Number(lines[0]);
    lines.shift();
  }
 
  nThisIteration = lines.length;
  
  // In multi chunk scenario:
  // chunk1: lines.length != n, lines.length + linesProcessed != n, lastLine = '', lastLine = 'something' at end
  // chunk2: lines.length != n, lines.length + linesProcessed != n, lastLine = 'something', lastLine = 'something else' at end
  // chunk2: lines.length != n, lines.length + linesProcessed == n, lastLine = 'something else'
  //guard for break in middle of last line

   // push the lastline into 
  if(lastLine.length > 0) {
    lines[0] = lastLine + lines[0];
    lastLine = '';
  }
  
  if( lines.length + linesProcessed != n && chunk[chunk.length - 1] !== "\n" ) {
     lastLine = lines[--nThisIteration];
     while( lastLine.length == 0) {
       lastLine = lines[--nThisIteration];
       lines.pop();
     }
   }
  
  for(var i=0; i<nThisIteration ; i++) {
    var num = Number(lines[i]);
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
  }
  linesProcessed += nThisIteration;

});

process.stdin.on('end', function() {
  for(var i = min; i < max; i++) {
    if( arr[i]) {
      while(arr[i]--) {
        process.stdout.write( i + "\n");
        }    
    }
  }
  elapsed_time("Execution");
//  var util = require('util');
//  console.log(util.inspect(process.memoryUsage()));
  
});
  
