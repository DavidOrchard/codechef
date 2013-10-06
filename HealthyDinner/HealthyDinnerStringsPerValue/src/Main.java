import java.io.*;
import java.util.StringTokenizer;
public class Main {
  
  int k;
  int[] proteins;
  String ingredients;
  int desiredProtein;
  String[] bestMatch;
  
  
  public Main(int k, int[] proteins, String ingredients, int desiredProtein) {
    this.k = k;
    this.proteins = proteins;
    this.ingredients = ingredients;
    this.desiredProtein = desiredProtein;
    this.bestMatch = new String[desiredProtein + 1];    
  }
  
  public String findBestIngredients( ) {
	 bestMatch[desiredProtein] = "";
	 for(int i = 0; i < ingredients.length(); i++) {
	    iterate(i);
	 }
    
    return bestMatch[0] == null ? "IMPOSSIBLE" : bestMatch[0];    
  }
  
  private void iterate( int currIndex) {
     int thisProtein = proteins[ingredients.charAt(currIndex) - 'a'];
     for(int j = 0; j <= desiredProtein; j++) {
    	 if(bestMatch[j] == null || j - thisProtein < 0) continue;
         int total = j - thisProtein;

        String newThisProteinString = bestMatch[j] + ingredients.charAt(currIndex);
        if(bestMatch[total] == null || bestMatch[total].compareTo(newThisProteinString) > 0 ) {
        	bestMatch[total] = newThisProteinString;
        }       
      }     
    }
  
  public static void main(String [] args) throws Exception {
    
//   File file = new File("/Users/dave/code/algorithms/codechef/HealthyDinner/src/sample.txt");

//    BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    
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
