import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

// each node has a name and n vertices with weights and targets
// Each node could also have a current weight and previous to track graph traversal.

public class Main {
	
	public class Edge {
		private final int target;
		private final int weight;
		private final char direction; // debugging hint
		
		public Edge(int target, int weight, char direction){
			this.target = target;
			this.weight = weight;
			this.direction = direction;
		}
		
		public int getTarget(){
			return this.target;
		}
		
		public int getWeight() {
			return this.weight;
		}

		public char getDirection() {
			return direction;
		}
	}
	
	public class Node {
		private final String name;
		private final ArrayList<Edge> edges = new ArrayList<Edge>();
		private int sum = Integer.MAX_VALUE;
		private int prev = -1;
		private final int weight;
		
		public Node(String name, int weight) {
			this.name = name;
			this.weight = weight;
		}
		
		
		public void addEdge(int dest, int weight, char direction) {
			edges.add(new Edge(dest,weight, direction));
		}
		
		public void addPrevious(int sum, int previous) {
			this.setSum(sum);
			this.setPrev(previous);
		}


		public String getName() {
			return name;
		}

		public int getSum() {
			return sum;
		}


		public void setSum(int sum) {
			this.sum = sum;
		}


		public int getPrev() {
			return prev;
		}


		public void setPrev(int prev) {
			this.prev = prev;
		}
		
		public ArrayList<Edge> getEdges() {
			return edges;
		}


		public int getWeight() {
			return weight;
		}
	}
		
	ArrayList<Node> tree = new ArrayList<Node>();
	
	public void addNode(String name){
		tree.add(new Node(name, 0));
		
	}
	
	public void addNode(String name, int nodeWeight){
		tree.add(new Node(name, nodeWeight));
		
	}
	
	
	public void addEdge(int src, int dest, int weight, char direction){
		tree.get(src).addEdge(dest, weight, direction);
	}
	
	public int getTimeAtPositionWithDimensions(int x, int M, int y, int N) {
		Node n = tree.get(y * (M - 1) + x);
		return n.sum;
		
	}
	public void dijkstra(boolean print) {
		int startNode = 0;
		int i = startNode;
		while(i < tree.size()) {
			Node node = tree.get(i);
			if( print) {
				System.out.printf("Node %d: weight = %d; sum = %d; prev = %d\n", i, node.getWeight(), node.getSum(), node.getPrev());
			} else {
				if( i == 0 ) {// first Node
					node.sum = node.weight;
				}			
			}
			startNode = dijkstraOnNode(node, i, print);
			if( startNode >= i) {
				i++;
			} else {
				i = startNode;
			}
		}
	}
	
	public int dijkstraOnNode(Node node, int curr, boolean print) {
		ArrayList<Edge> edges = node.getEdges();
		for( int j = 0; j < edges.size(); j++) {
			Edge edge = edges.get(j);
			Node target = tree.get(edge.getTarget());
			if( !print ) {
				int targetSum = target.getSum();
				if (node.sum + edge.weight + target.weight < targetSum) {
					target.setSum(node.sum + edge.weight + target.weight);
					target.setPrev(curr);
					if( edge.getTarget() < curr ) {// backtracking
						return edge.getTarget();
					}
				}
			} else {
				System.out.printf("  Edge %d: target = %d, direction = %c, weight = %d\n", j, edge.getTarget(), edge.getDirection(), edge.getWeight());
			}				
		}
		return curr;
	}

	public static void main(String[] args){
		Main graph = new Main();
/*		int M = 3;// width, x
		int N = 4;// height, y
		graph.addNode("11",2);
		graph.addNode("12",3);
		graph.addNode("13",2);
		graph.addNode("21",2);
		graph.addNode("22",5);
		graph.addNode("23",1);
		graph.addNode("31",5);
		graph.addNode("32",3);
		graph.addNode("33",1);
		graph.addNode("41",3);
		graph.addNode("42",1);
		graph.addNode("43",1);
*/
		int M;
		int N;
		// File file = new File("/Users/dave/code/algorithms/codechef/nikhilsDungeon/sample2.txt");
		Scanner scanner = null;
		try {
            scanner = new Scanner(System.in);
        } catch (Exception e) {
        System.out.println(e);
        }
         	
		M = scanner.nextInt(); // # of rows (ie y)
		N = scanner.nextInt(); // # of columns (ie x)

		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				int t = scanner.nextInt();
				graph.addNode("", t);
			}
		}
		
		int locY = scanner.nextInt();
		int locX = scanner.nextInt();
		int time = scanner.nextInt();
		
		// M = 3; N = 4
		// 0,0 = 0; 0,1 = 1; 0,2 = 2; 0,3 = 3; 1,0 = 4; 
		// 3,4 = 11 (last node x = 2, y = 3, m = 3; y * M + x = 11, target = 
		for(int y = 0; y < M; y++) {
			for( int x = 0; x < N; x++) {
				// Add Edges in all 4 directions except where x == 0 or x == M - 1 or y == 0 or y == N - 1
				// left
				if( x > 0) {
					graph.addEdge(y * N + x,  y * N + x - 1,  0, 'L');
				}
				// up
				if( y > 0) {
					graph.addEdge(y * N + x,  (y - 1 )* N + x,  0, 'U');					
				}
				// right
				if( x < N - 1) {
					graph.addEdge(y * N + x,  y * N + x + 1,  0, 'R');					
				}
				// down
				if( y < M - 1) {
					graph.addEdge(y * N + x,  (y + 1 )* N + x,  0, 'D');					
				}
			}
		}
		//graph.dijkstra(true);
		graph.dijkstra(false);
		//graph.dijkstra(true);
		int fastest = graph.getTimeAtPositionWithDimensions(locX - 1, M, locY - 1, N);
		if( fastest <= time){
			System.out.println("YES");
			System.out.println(time - fastest );
		} else {
			System.out.println("NO");
		}
	}
}
