import java.io.*;
import java.util.StringTokenizer;
public class Main {
  
  int k;
  int[] proteins;
  String ingredients;
  int desiredProtein;
  String bestMatch = null;
  
  
  public Main(int k, int[] proteins, String ingredients, int desiredProtein) {
    this.k = k;
    this.proteins = proteins;
    this.ingredients = ingredients;
    this.desiredProtein = desiredProtein;
    
  }
  
  public String findBestIngredients( ) {
    iterate(0, "", 0);
    
    return bestMatch == null ? "IMPOSSIBLE" : bestMatch;    
  }
  
  public void iterate( int currentProtein, String thisProteinString, int currIndex) {
    for(int i = currIndex; i < ingredients.length(); i++) {
        int thisProtein = proteins[ingredients.charAt(i) - 97];
        // too high a count
        if( currentProtein + thisProtein > desiredProtein ) {
          continue;
        }
        String newThisProteinString = thisProteinString + ingredients.charAt(i);
        if( currentProtein + thisProtein == desiredProtein) {
        	if(bestMatch == null || newThisProteinString.compareTo(bestMatch) < 0) {
         		bestMatch = newThisProteinString;
        	}
          iterate(currentProtein, thisProteinString, i + 1);
        } else {
          iterate( currentProtein + thisProtein, newThisProteinString, i + 1);
        }
      }     
    }
  
  public static void main(String [] args) throws Exception {
    
   //File file = new File("/Users/dave/code/algorithms/codechef/HealthyDinner/src/sample.txt");

    //BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    
    // iterate over each case
    // for each, search for lowest string.
    
    int T = Integer.parseInt(r.readLine());
    for(int i = 0; i < T; i++) {
      StringTokenizer st = new StringTokenizer(r.readLine());
      int k = Integer.parseInt(st.nextToken());
      int[] proteins = new int[k];
      for( int j = 0; j < k; j++) {
          proteins[j] = Integer.parseInt(st.nextToken());
      }
      String ingredients = r.readLine();
      int desiredProtein = Integer.parseInt(r.readLine());
      Main m = new Main(k, proteins, ingredients, desiredProtein);
      String proteinOrder = m.findBestIngredients();
      System.out.println(proteinOrder);
    }
    r.close();
    
  }

}
