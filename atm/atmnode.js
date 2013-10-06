process.stdin.resume();
process.stdin.setEncoding('utf8');

process.stdin.on('data', function(chunk) {
  var stdinlines = chunk.split('\n');
  var nums = stdinlines[0].split(" ");
  var withdraw = parseFloat(nums[0]);
  var balance = parseFloat(nums[1]);
  if( withdraw % 5 == 0 ) {
    var debit = withdraw + 0.5;
    if( balance >= debit ) {
      balance-= debit;
    }    
  }
  process.stdout.write(balance.toFixed(2).toString() + "\n");
});

