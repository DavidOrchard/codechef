// Use javascript sort function
process.stdin.resume();
process.stdin.setEncoding('utf8');

function sorter(a,b){return a-b}  

var data = '';
var t;
var start = process.hrtime();

var elapsed_time = function(note){
    var precision = 3; // 3 decimal places
    var elapsed = process.hrtime(start)[1] / 1000000; // divide by a million to get nano to milli
    console.log(elapsed.toFixed(precision) + " ms - " + note); // print message + time
    start = process.hrtime(); // reset the timer
}

function numericize(elem, pos, self) {
    self[pos] = Number(elem);
    return true;
}

process.stdin.on('data', function(chunk) {
  data += chunk;
});

process.stdin.on('end', function() {
  var arr = data.split('\n');
  t = arr.shift();
  arr.every(numericize);
  arr.sort(sorter);
  
   process.stdout.write(arr.join("\n") + "\n");
   elapsed_time("Execution");
   var util = require('util');
   console.log(util.inspect(process.memoryUsage()));
   
});
  
