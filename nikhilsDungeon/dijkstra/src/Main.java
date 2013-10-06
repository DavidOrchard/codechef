import java.io.File;
import java.util.Scanner;

// Nikhil's dungeon using dijkstra's algorithm.  Matrix is implemented as a list of nodes, nodes containing edges to other nodes
// Variants include weights on edges and nodes, hints on direction for debugging.  No setters or getters are used for performance tweaking

// each node has n edges with weights and targets
// each node could also have a current weight and previous to track graph traversal.
// each edge has target, weight

public class Main {
	
	public class Edge {
		private final int target;
		//private final int weight;
		//private final char direction; // debugging hint
		
/*		public Edge(int target, int weight, char direction){
			this.target = target;
			this.weight = weight;
			this.direction = direction;
		}		
*/
		public Edge(int target){
			this.target = target;
		}		
	}
	
	public class Node {
		//private final String name; // for showing path 
		private final Edge[] edges = new Edge[4]; // ArrayList<Edge>();
		private int sum = Integer.MAX_VALUE;
		private int prev = -1;
		private boolean visited = false;
		private final int weight;
		
		/*public Node(String name, int weight) {
			this.name = name;
			this.weight = weight;
		}
		*/

		public Node(int weight) {
			this.weight = weight;
		}
				
		public void addPrevious(int sum, int previous) {
			this.sum = sum;
			this.prev = previous;
		}
	}

	Node[] tree = null;	
	
	public void addEdge(int src, int dest, int place/*, int weight*/){
		tree[src].edges[place] = dest == -1 ? null : new Edge(dest /*, weight*/);
	}
	
	// M = 6, N = 5, x = 3, y = 5; loc should be 28 ( Y * N + x )
	// M = 1, N = 70, x = 50, y = 0; loc should be 50 (Y * N + x)
	// M = 70, N = 1, x = 0, y = 50; loc should be 50 (Y *N + x)
	
	public int getPositionWithDimensions(int x, int M, int y, int N) {
		return(y * N + x);
	}
	
	public void dijkstra() {
		dijkstra(false);
	}
	
	// There is an optional print boolean to just print the graph
	public void dijkstra(boolean print) {
		tree[0].sum = tree[0].weight;
		tree[0].visited = true;
		for(int i = 0; i < tree.length; i++) {
			Node node = tree[i];
			if( print) {
				System.out.printf("Node %d: weight = %d; sum = %d; prev = %d\n", i, node.weight, node.sum, node.prev);
				dijkstraOnNode(node, i, print);			

			} else {
				Node minNode = node;
				int minDist = Integer.MAX_VALUE;
				for(int j = 0; j < tree.length; j++) {								
					if(tree[j].sum < minDist && !tree[j].visited) {
						minNode = tree[j];
						minDist = minNode.sum;
					}
				}			
				dijkstraOnNode(minNode, i, print);			
			}
		}
	}
	
	public void dijkstraOnNode(Node node, int curr, boolean print) {
		Edge[] edges = node.edges;
		for( int j = 0; j < edges.length; j++) {
			Edge edge = edges[j];
			if( edge == null) {
				continue;
			}
			Node target = tree[edge.target];
			if( !print ) {
				int targetSum = target.sum;
				if (node.sum + /*edge.weight + */ target.weight < targetSum) {
					target.sum = node.sum + /*edge.weight + */ target.weight;
					target.prev = curr;
				}
			} else {
				System.out.printf("  Edge %d: target = %d\n", j, edge.target/*, direction = %c, weight = %d, edge.weight, edge.direction*/);
			}				
		}
		if( !print ) {
			node.visited = true;
		}
	}

	public static void main(String[] args){
		Main graph = new Main();

		int M;
		int N;
		Scanner scanner = null;
		try {
//			File file = new File("/Users/dave/code/algorithms/djikstra/src/sample2.txt");
//            scanner = new Scanner(file);
            scanner = new Scanner(System.in);
        } catch (Exception e) {
        	System.out.println(e);
        }
         	
		M = scanner.nextInt(); // # of rows (ie y)
		N = scanner.nextInt(); // # of columns (ie x)
		graph.tree = new Node[N*M];		

		for(int y = 0; y < M; y++) {
			for(int x = 0; x < N; x++) {
				int t = scanner.nextInt();
				int currNode = graph.getPositionWithDimensions(x, M, y, N);
				graph.tree[currNode] = graph.new Node(t);
				// Add Edges in all 4 directions except where x == 0 or x == N - 1 or y == 0 or y == M - 1
				// left
				graph.addEdge(currNode, ( x > 0 ) ? currNode - 1 : -1 , 0/*, 0, 'L'*/);
				// up
				graph.addEdge(currNode, ( y > 0 ) ? currNode - N : -1, 1/*, 0, 'U'*/);					
				// right
				graph.addEdge(currNode, ( x + 1 < N ) ? currNode + 1 : -1 , 2/*, 0, 'R'*/);					
				// down
				graph.addEdge(currNode, (y + 1 < M ) ? currNode + N : -1,  3/*, 0, 'D'*/);					

			}
		}
		int locY = scanner.nextInt();
		int locX = scanner.nextInt();
		int time = scanner.nextInt();
		
		//graph.dijkstra(true);
		graph.dijkstra();
		//graph.dijkstra(true);
		int fastest = graph.tree[graph.getPositionWithDimensions(locX - 1, M, locY - 1, N)].sum;
		if( fastest <= time){
			System.out.println("YES");
			System.out.println(time - fastest );
		} else {
			System.out.println("NO");
		}
	}
}
