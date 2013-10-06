import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/* sample data
 * 
3

2
0 0
0 1

3
0 0
1 1
2 2

4
0 0
1 10
1 5
2 2
 */
public class Main {

	int t; // test cases;
	Point[][] cases;
	double[] results;

	public class Point implements Comparable<Point>{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int compareTo(Point comparePoint) {
			int diff = this.x - comparePoint.x;
			if(diff == 0) {
				diff = comparePoint.y - this.y;
			}
			return diff;
		}
	}

	public void displayPoints() {
		for(int i = 0; i < t; i++) {
			System.out.println("Test case "+i);
			for(int j = 0; j < cases[i].length; j++) {
				System.out.println("  " + cases[i][j].x + ", " + cases[i][j].y);
			}
		}
	}
	
	public void displayResults() {
		for(int i = 0; i < t; i++) {
			System.out.println(String.format( "%.2f", results[i] ));
		}
	}
	
	public static void main(String args[]) {
		Main m = new Main();
		m.doit();
	}
	
	public void doit() {
		BufferedReader input = null;
		try {
			//input = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/Users/dave/code/algorithms/longwaytofriendshouse/src/sample.txt")))) ;
			input = new BufferedReader(new InputStreamReader(System.in)) ;
			     	
			t = Integer.parseInt(input.readLine()) ;
			cases = new Point[t][];
			results = new double[t];
			
			for (int i = 0; i < t; i++) {
				input.readLine();
				int N = Integer.parseInt(input.readLine()) ;
				Point[] testcase = new Point[N];
				cases[i] = testcase;
				for( int j = 0; j < N; j++ ) {
					String[] tmp = input.readLine().split(" ") ;
					testcase[j] = new Point(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
				}
				Arrays.sort(testcase);
				results[i] = 0.0;
				for( int j = 0; j < N - 1; j++) {
					double dist = Math.sqrt( Math.pow(testcase[j + 1].x - testcase[j].x, 2) + Math.pow(testcase[j + 1].y - testcase[j].y, 2));
					results[i] += dist;
				}
			}
			//displayPoints();
			displayResults();
		} catch (Exception e) {
			System.out.println(e);
		}

		
	}
}
