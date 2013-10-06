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


// algorithm from http://en.wikipedia.org/wiki/Merge_sort
function merge_sort(m) {
    // if list size is 0 (empty) or 1, consider it sorted and return it
    // (using less than or equal prevents infinite recursion for a zero length m)
    if(m.length <= 1 ) {
      return m;
    }
    // else list size is > 1, so split the list into two sublists
    var left = [];
    var right = [];
    var middle = Math.floor(m.length / 2);
    
    for( var i = 0; i < middle; i++) {
      left.push(m[i]);
    }
    for( var i = middle; i < m.length; i++) {
      right.push(m[i]);
    }
    // recursively call merge_sort() to further split each sublist
    // until sublist size is 1
    left = merge_sort(left);
    right = merge_sort(right);
    // merge the sublists returned from prior calls to merge_sort()
    // and return the resulting merged sublist
    var newArr = merge(left, right);
    console.log("left size = " + left.length + ", right size = "+ right.length + ", middle = " + middle + ", merged size = " + newArr.length);
    return newArr;
}
function merge(left, right) {
  var result = [];
  while (left.length > 0 || right.length > 0) {
      if (left.length > 0 && right.length > 0 ) {
        if (left[0] <= right[0]) {
          result.push(left.shift());
        } else {
          result.push(right.shift());
        }
      } else if (left.length > 0) {
        result.push(left.shift());
      } else if (right.length > 0 ) {
        result.push(right.shift());
      }
  }
  return result;
}

function numericize(elem, pos, self) {
    self[pos] = Number(elem);
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
  
  var sortedArray = merge_sort(arr);
  
//  var arr = data.split('\n').sort(sorter);
  // don't join to prevent creating a big long string of numbers
  //sortedArray.every(printElem);
  elapsed_time("Execution");
  
});
  
