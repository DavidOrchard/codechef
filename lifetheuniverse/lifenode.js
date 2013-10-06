process.stdin.resume();
process.stdin.setEncoding('utf8');

process.stdin.on('data', function(chunk) {
  var stdinlines = chunk.split('\n');
  
  var s = true;
  var lineNum = 0;
  while (s) {
    s = stdinlines[lineNum++];

    if (s) {
      if(+s !== 42) {
        process.stdout.write(s+ "\n");
      } else {
        break;
      }
    }
  }
});

