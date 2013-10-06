// Use javascript sort function
process.stdin.resume();
process.stdin.setEncoding('utf8');

function sorter(a,b){return Number(a)-Number(b)}  
var start = process.hrtime();

var elapsed_time = function(note){
    var precision = 3; // 3 decimal places
    var elapsed = process.hrtime(start)[1] / 1000000; // divide by a million to get nano to milli
    console.log(elapsed.toFixed(precision) + " ms - " + note); // print message + time
    start = process.hrtime(); // reset the timer
}

var data = '';
process.stdin.on('data', function(chunk) {
  data += chunk;
});

process.stdin.on('end', function() {
  var arr = data.split('\n');
  t = arr.shift();
  
   process.stdout.write(arr.sort(sorter).join("\n") + "\n");
   elapsed_time("Execution");
   var util = require('util');
   console.log(util.inspect(process.memoryUsage()));
   
});
  
