package alienchef;

import java.util.ArrayList;
import java.util.Scanner;

public class AlienChef {
	public static void parseLines(String[] lines, ArrayList<String[]> showsArray, ArrayList<String[]> aliensArray) {
		  boolean s = true;
		  int lineNum = 0;
		  int N = Integer.parseInt(lines[lineNum++]);
		  if( N <0 || N > 100000) {
		    throw( new Error("N out of range"));
		  }
		  for(int i = 0; i<N; i++) {
		    showsArray.add(lines[lineNum++].split(" "));
		    if( showsArray.get(i).length != 2) {
		      throw( new Error("Show array at index " + i + " is not 2 long, text = " + showsArray.get(i)));
		    }
		  }      
		  int Q = Integer.parseInt(lines[lineNum++]);
		  if(Q<0 || Q > 5000) {
		     throw( new Error("Q out of range"));
		   }
		   for(int i = 0; i<Q; i++) {
		     aliensArray.add(lines[lineNum++].split(" "));
		     if(Integer.parseInt(aliensArray.get(i)[0]) != aliensArray.get(i).length - 1) {
		       throw( new Error("Alien line has incorrect length: " + s));
		     }
		   }          
		}
	
	public static void main(String args[]) {
		ArrayList<String> lines = new ArrayList<>();
		
		Scanner input = new Scanner(System.in);
		while(input.hasNextLine()) {
			String line = input.nextLine();
			lines.add(line);
		}
		input.close();
		String[] linesArray = new String[lines.size()];
		linesArray = lines.toArray(linesArray);
		ArrayList<String[]> showsArray = new ArrayList<>();
		ArrayList<String[]> aliensArray = new ArrayList<>();
		parseLines(linesArray, showsArray, aliensArray);
	}

}
