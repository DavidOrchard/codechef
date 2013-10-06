// Use in place quicksort function with halfway index of array as pivot
process.stdin.resume();
process.stdin.setEncoding('utf8');
var start = process.hrtime();

var elapsed_time = function(note){
    var precision = 3; // 3 decimal places
    var elapsed = process.hrtime(start)[1] / 1000000; // divide by a million to get nano to milli
    console.log(elapsed.toFixed(precision) + " ms - " + note); // print message + time
    start = process.hrtime(); // reset the timer
}


function qsortOutOfPlace(arr){
  if( arr.length <= 1 ) {
      return arr;  // an array of zero or one elements is already sorted
  }
  var pivot = arr.pop();
  var less = [];
  var great = [];
  for( var i = 0; i < arr.length; i++){
    if( arr[i] <= pivot) {
      less.push(arr[i]);
    } else {
      greater.push(arr[i]);
    }
  }
  return concat( concat(qsort(less), pivot), qsort(greater));
}

function quicksortInPlace(arr, left, right) {

  // If the list has 2 or more items
  if( left < right ) {
    // See "Choice of pivot" section below for possible choices
    var pivotIndex = Math.floor((left + right) / 2);

    // Get lists of bigger and smaller items and final position of pivot
    pivotIndex = partition(arr, left, right, pivotIndex)

    // Recursively sort elements smaller than the pivot
    quicksortInPlace(arr, left, pivotIndex - 1)

    // Recursively sort elements at least as big as the pivot
    quicksortInPlace(arr, pivotIndex + 1, right)
    }
}

// left is the index of the leftmost element of the subarray
 // right is the index of the rightmost element of the subarray (inclusive)
 //   number of elements in subarray = right-left+1
 // from http://en.wikipedia.org/wiki/Quicksort
 function partition(arr, left, right, pivotIndex) {
    var pivotValue = arr[pivotIndex];    
    arr[pivotIndex] = arr[right];
    arr[right] = pivotValue;
    var storeIndex = left;
    for(var i = left; i < right; i++ ) { // left ≤ i < right
      if( arr[i] <= pivotValue ) {
        var tmp = arr[storeIndex];
        arr[storeIndex] = arr[i];
        arr[i] = tmp;
        storeIndex += 1;
        }
      }
    tmp = arr[right];
    arr[right] = arr[storeIndex];
    arr[storeIndex] = tmp;   // Move pivot to its final place
    return storeIndex;
}

function numericize(elem, pos, self) {
    self[pos] = parseInt(elem);
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
  var sortedArray = data.split('\n');
  var t = sortedArray.shift();
  while( sortedArray.length > t) {
    sortedArray.pop();
  }
  // make numbers out of strings;
  sortedArray.every(numericize);
  
  quicksortInPlace(sortedArray, 0, sortedArray.length - 1);
  
//  var sortedArray = data.split('\n').sort(sorter);
  // don't join to prevent creating a big long string of numbers
//  sortedArray.every(printElem);
  elapsed_time("Execution");
  
});
  
