var stdin = process.stdin;
stdin.resume();
stdin.setEncoding('utf8');
data = "";
stdin.on('data', function (chunk) {
data += chunk;
});
process.stdin.on('end', function() {
var lines = data.split(/\W+/);
var NoTests = parseInt(lines[0]);
for (var i = 1; i <= NoTests; i++) {
var Number = parseInt(lines[i]);
var pow5 = 0;
for (var j = 5; j <= Number; j = j * 5) {
pow5 += Math.floor(Number / j);
}
process.stdout.write(pow5 + '\n');
}
//process.exit();
});