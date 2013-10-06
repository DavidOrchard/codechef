// Equivalent in effect to the Java declaration import java.io.*;
importPackage(java.io);
importPackage(java.lang);  
// "in" is a keyword in JavaScript. 
// In JavaScript you could query for an attribute using [] syntax: 
var reader = new BufferedReader( new InputStreamReader(System['in']) );

// We use JavaScript's dynamic typing here to let s be a boolean first, and later on a string...
var s = true;
while (s) {
  s = reader.readLine();

  if (s) {
    if(+s !== 42) {
      System.out.println(s);
    } else {
      break;
    }
  }
}
