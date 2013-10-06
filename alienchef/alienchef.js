// version with coordinate compression but no b-tree index
process.stdin.resume();
process.stdin.setEncoding('utf8');

var shows = new Array();
var aliensVisits = new Array();

function parseLines(lines, shows, aliensVisits) {
  var s = true;
  var lineNum = 0;
  var N = +lines[lineNum++];
  if( N <0 || N > 100000) {
    throw( new Error("N out of range"));
    exit;
  }
  for(var i = 0; i<N; i++) {
    shows[i] = lines[lineNum++].split(" ");
    if( shows[i].length != 2) {
      throw( new Error("Show array at index " + i + " is not 2 long, text = " + shows[i]));
    }
  }      
  var Q = +lines[lineNum++];
  if(Q<0 || Q > 5000) {
     throw( new Error("Q out of range"));
   }
   for(var i = 0; i<Q; i++) {
     s = lines[lineNum++];
     aliensVisits[i] = s.split(" ");
     if(aliensVisits[i][0] != aliensVisits[i].length - 1) {
       throw( new Error("Alien line has incorrect length: " + s));
     }
     aliensVisits[i].shift();
   }          
}

function coordcompression( shows, aliensVisitsArray) {
  var timeArray = [];
  for(var i = 0; i < aliensVisitsArray.length; i++) {
    var alienVisits = aliensVisitsArray[i];
    for( var j = 0; j < alienVisits.length; j++ ) {
      timeArray.push(+alienVisits[j]);
    }
  }
  for(var i = 0; i< shows.length; i++ ){
    timeArray.push(+shows[i][0]);
    timeArray.push(+shows[i][1]);
  }
  timeArray.sort(function(a,b){return a-b});
  timeArray = timeArray.filter(function(elem, pos) {
      return timeArray.indexOf(elem) == pos;
  })
  
  for(var i = 0; i < aliensVisitsArray.length; i++) {
    var alienVisits = aliensVisitsArray[i];
    for( var j = 0; j < alienVisits.length; j++ ) {
      alienVisits[j] = timeArray.indexOf(+alienVisits[j]);
    }
  }
  for(var i = 0; i< shows.length; i++ ){    
    shows[i][0] = +timeArray.indexOf(+shows[i][0]);
    shows[i][1] = +timeArray.indexOf(+shows[i][1]);    
  }  
}

function checkVisits( shows, aliensVisits) {
  for(var i = 0; i < aliensVisits.length; i++) {
    var alienVisits = aliensVisits[i];
    var showMatches = new Array(shows.length);
    for( var j = 0; j < alienVisits.length; j++ ) {
      var alienTime = alienVisits[j];
      for( var k = 0; k < shows.length; k ++ ) {
        if(+shows[k][0] <= alienTime && +shows[k][1] >= alienTime ) {
          showMatches[k] = 1;
        }
      }
    }
    var numShows = 0;
    for(var k = 0; k < showMatches.length; k++) {
      if(showMatches[k]){
        numShows++;
      }
    }
    process.stdout.write(numShows + "\n");
  }
}

function doit(lines){
  try {
    parseLines(lines, shows, aliensVisits);
    coordcompression(shows, aliensVisits);
    checkVisits(shows, aliensVisits);
  } catch ( e) {
    process.stdout.write(e.message + "\n");
  }  
}
var data = '';
process.stdin.on('data', function(chunk) {
  data += chunk;
});
  
process.stdin.on('end', function() {
  var stdinlines = data.split('\n');
  doit(stdinlines)
});
  
