var showsArray = new Array();
var aliensArray = new Array();
var usingNode = false;

if(usingNode ) {
  process.stdin.resume();
  process.stdin.setEncoding('utf8');
} else {
  // Equivalent in effect to the Java declaration import java.io.*;
  importPackage(java.io);
  importPackage(java.lang);
  
}

var parseLines = function(lines, showsArray, aliensArray) {
  var s = true;
  var lineNum = 0;
  var N = +lines[lineNum++];
  if( N <0 || N > 100000) {
    throw( new Error("N out of range"));
    exit;
  }
  for(var i = 0; i<N; i++) {
    showsArray[i] = lines[lineNum++].split(" ");
    if( showsArray[i].length != 2) {
      throw( new Error("Show array at index " + i + " is not 2 long, text = " + showsArray[i]));
    }
  }      
  var Q = +lines[lineNum++];
  if(Q<0 || Q > 5000) {
     throw( new Error("Q out of range"));
   }
   for(var i = 0; i<Q; i++) {
     s = lines[lineNum++];
     aliensArray[i] = s.split(" ");
     if(aliensArray[i][0] != aliensArray[i].length - 1) {
       throw( new Error("Alien line has incorrect length: " + s));
     }
     aliensArray[i].shift();
   }          

};

var checkVisits = function( showsArray, aliensArray) {
  for(var i = 0; i < aliensArray.length; i++) {
    var alienVisits = aliensArray[i];
    var showMatches = new Array(showsArray.length);
    for( var j = 0; j < alienVisits.length; j++ ) {
      var alienTime = alienVisits[j];
      for( var k = 0; k < showsArray.length; k ++ ) {
        if(+showsArray[k][0] <= alienTime && +showsArray[k][1] >= alienTime ) {
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
    if(usingNode) {
      process.stdout.write(numShows + "\n");
    } else {
      System.out.println(numShows);
    }
  }
}

var doit = function (lines){
  try {
    parseLines(lines, showsArray, aliensArray);
    checkVisits(showsArray, aliensArray);
  } catch ( e) {
    if(usingNode) {
      process.stdout.write(e.message + "\n");
    } else {
      System.out.println(e.message)
    }    
  }  
};

if( usingNode ) {
  process.stdin.on('data', function(chunk) {
    var stdinlines = chunk.split('\n');
    doit(stdinlines)
  });
} else {
  // "in" is a keyword in JavaScript. 
  // In JavaScript you could query for an attribute using [] syntax: 
  var reader = new BufferedReader( new InputStreamReader(System['in']) );

  // We use JavaScript's dynamic typing here to let s be a boolean first, and later on a string...
  var s = true;
  var stdinlines = new Array();
  
  while (s) {
      s = reader.readLine();

      if (s) {
        stdinlines.push(s);
      }
  }
  doit(stdinlines);  
}