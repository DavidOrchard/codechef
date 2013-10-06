import java.util.ArrayList;
import java.util.Scanner;

// each node has a name and n vertices with weights and targets
// Each node could also have a current weight and previous to track graph traversal.

public class MainNoGetters {
	
	public class Edge {
		private final int target;
		private final int weight;
		private final char direction; // debugging hint
		
		public Edge(int target, int weight, char direction){
			this.target = target;
			this.weight = weight;
			this.direction = direction;
		}
		
	}
	
	public class Node {
		private final String name;
		private final ArrayList<Edge> edges = new ArrayList<Edge>();
		private int sum = Integer.MAX_VALUE;
		private int prev = -1;
		private boolean visited = false;
		private final int weight;
		
		public Node(String name, int weight) {
			this.name = name;
			this.weight = weight;
		}
		
		
		public void addEdge(int dest, int weight, char direction) {
			edges.add(new Edge(dest,weight, direction));
		}
		
		public void addPrevious(int sum, int previous) {
			this.sum = sum;
			this.prev = previous;
		}


	Node[] tree = null;	
	
	public void addEdge(int src, int dest, int weight, char direction){
		tree[src].addEdge(dest, weight, direction);
	}
	
	// M = 6, N = 5, x = 3, y = 5; loc should be 28 ( Y * N + x )
	// M = 1, N = 70, x = 50, y = 0; loc should be 50 (Y * N + x)
	// M = 70, N = 1, x = 0, y = 50; loc should be 50 (Y *N + x)
	
	public int getTimeAtPositionWithDimensions(int x, int M, int y, int N) {
		Node n = tree[x + y * N];
		return n.sum;
		
	}
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
		ArrayList<Edge> edges = node.edges;
		for( int j = 0; j < edges.size(); j++) {
			Edge edge = edges.get(j);
			Node target = tree[edge.target];
			if( !print ) {
				int targetSum = target.sum;
				if (node.sum + edge.weight + target.weight < targetSum) {
					target.sum = node.sum + edge.weight + target.weight;
					target.prev = curr;
				}
			} else {
				System.out.printf("  Edge %d: target = %d, direction = %c, weight = %d\n", j, edge.target, edge.direction, edge.weight);
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
			//File file = new File("/Users/dave/code/algorithms/djikstra/src/sample2.txt");
            //scanner = new Scanner(file);
            scanner = new Scanner(System.in);
        } catch (Exception e) {
        System.out.println(e);
        }
         	
		M = scanner.nextInt(); // # of rows (ie y)
		N = scanner.nextInt(); // # of columns (ie x)
		graph.tree = new Node[N*M];		

		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				int t = scanner.nextInt();
				graph.tree[i * N + j] = graph.new Node("", t);
			}
		}
		
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
		int locY = scanner.nextInt();
		int locX = scanner.nextInt();
		int time = scanner.nextInt();
		
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
