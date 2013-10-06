var stdin = process.stdin;
stdin.resume();
stdin.setEncoding('utf8');
var data = '';
stdin.on('data', function(chunk) {
  data += chunk;
});
stdin.on('end', function() {
  var lines = data.split('\n');
  var T = +lines[0];
  for(var i = 1; i<=T ; i++) {
    var z = 0;
    var num = +lines[i];
    for(var j = 5; j <= num; j = j * 5) {
      z += Math.floor(num/j);
    }    
    process.stdout.write(z + "\n");
  }
});



