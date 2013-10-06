// merge sort
process.stdin.resume();
process.stdin.setEncoding('utf8');
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

function insertion_sort(item, arr) {
  if(arr.length == 0) {
    arr.push(item);
    return true;
  }
  var i = 0;
  while( arr[i] < item ) {
    i++;
  }
  arr.splice(i, 0, item);
  return true;
}

function printElem(elem) {
  process.stdout.write(elem + "\n");
  return true;
}

var data = '';
process.stdin.on('data', function(chunk) {
  data += chunk;
});

process.stdin.on('end', function() {
  var arr = data.split('\n');
  var t = arr.shift();
  while( arr.length > t) {
    arr.pop();
  }
  // make numbers out of strings;
  arr.every(numericize);
  var sortedArray = [];
  
  arr.every(function(element,index, array) { return insertion_sort(element, this);}, sortedArray);
  
//  var arr = data.split('\n').sort(sorter);
  // don't join to prevent creating a big long string of numbers
  sortedArray.every(printElem);
  elapsed_time("Execution");
  
});
  
