var stdin = process.stdin;
stdin.resume();
stdin.setEncoding('utf8');
var data = '';
var line0;
var lastLine = '';
var n, k;
var divisible = 0;
var nThisIteration = 0;
var linesProcessed = 0;
stdin.on('data', function(chunk) {
  var lines = chunk.split('\n');
  if(!line0) {
    line0 = lines[0].split(' ');
    n = +line0[0];
    k = +line0[1];
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
  
  if( lines.length + linesProcessed != n ) {
     nThisIteration -= 1;
     lastLine = lines[nThisIteration];
   }
  
  for(var i=0; i<nThisIteration ; i++) {
    if(+lines[i] % k == 0) {
      divisible++;
    }   
  }
  linesProcessed += nThisIteration;
});

stdin.on('end', function(){
  process.stdout.write(divisible + "\n");
})


