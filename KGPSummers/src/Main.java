// travelling salesman problem.
// 1 <= T <= 10
// 0 <= N <= 2500
// Make a min-heap from a binary to store each of the visited nodes
// nodes contain the visited nodes and the total sweat at each node.
// start with direct path with no nodes visited.  Set min to that
// pull node off and calculate the visited nodes and total sweat for each possible next node and put on.
// when pulling nodes off the min-heap, if the total sweat is less than current min then throw away the rest.

import java.util.BitSet;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.Math;

public class Main {

  public static int sweat(int[] room1, int[] room2) {
    return (int)(Math.pow((room1[0] - room2[0]), 2) + Math.pow((room1[1] - room2[1]), 2));
  }
  
  BinarySearchTree t;
  int N;
  int minSweat;
  BitSet minSweatRooms;
  int[] studentsRoom;
  int[] classroom;
  int[][] trees;
  
  public Main( int[] studentsRoom, int[] classroom, int[][] trees) {
	 N = trees.length;
	 this.studentsRoom = studentsRoom;
	 this.classroom = classroom;
	 this.trees = trees;
	 t = new BinarySearchTree(N);	 
	 minSweat = sweat(classroom, studentsRoom);
	 minSweatRooms = new BitSet(N);
	 KGPNode n = new KGPNode(0, 0, minSweatRooms, N);
	 t.add(n); // add each node 
}
  
  public void search() {
	    // deal with studentsRoom -> tree -> classRoom
		while(!t.isEmpty()) {
			 iterateOnTree();
		}
  }
  
  	public void iterateOnTree() {
		    KGPNode n = t.remove();
		    
		    // throw away trees with sweat > current min
		    if(n.sweat >= minSweat) {
		      return;
		    }
		    if( n.sweat > 0){ // must have visited at least 1 tree before trying the classroom
		    	int newSweat = n.sweat + sweat(trees[n.currentTree], classroom);
		    	if( minSweat > newSweat) {
		    		minSweat = newSweat;
		    		minSweatRooms = (BitSet) n.visited.clone();
		    	}
		    	if(n.allVisited()) {
				    return;
		    	}
		    }
		    int[] location = n.currentTree > 0 ? trees[n.currentTree] : studentsRoom;
		    // do each tree
	        for(int j = 0; j < trees.length; j++) {
		      if( !n.getBit(j)) {
		    	int thisSweat = n.sweat + sweat(location, trees[j]);
		        if( thisSweat < minSweat ) {
		            BitSet bs = (BitSet)n.getVisited().clone();
		            bs.set(j);
		            KGPNode nextNode = new KGPNode(thisSweat, j, bs, trees.length );
		            t.add(nextNode);
		        }         
		      }
		    }
	        return;
	  
  }
  
  public class DepthAndNode {
      int depth;
      BinaryTreeNode n;
      
      public DepthAndNode(int depth, BinaryTreeNode n) {
        this.depth = depth;
        this.n = n;
      }
    }

  public class BinaryTreeNode {
      BinaryTreeNode left = null;
      BinaryTreeNode right = null;
      BinaryTreeNode top = null;
      KGPNode n;
      
      // empty left and empty right = this node;
      // node in left and empty right = this node;
      // node in left and node in right, recurse;
      
      public BinaryTreeNode(KGPNode n){
       this.n = n;
       }
       
       // On BinaryTreeNode
       public KGPNode moveUp() {

        // not 2 edges so remove a reference to one of the items
        if( left == null && right == null) {
           return n;
        } else if( left == null && right != null) {
          BinaryTreeNode n = right;
          if( n.moveUp().compareTo(n.n) == 0) {
             right = null;
          }
          return n.n;
        } else if( left != null && right == null) {
          BinaryTreeNode n = left;
          if( n.moveUp().compareTo(n.n) == 0) {
           left = null;
          }
          return n.n;
        }
        if(left.n.compareTo(right.n) < 0 ) {
        	KGPNode temp = left.n;
        	KGPNode movedUp = left.moveUp();
        	if(movedUp.compareTo(left.n) == 0) {
        		if(left.left == null && left.right == null ){
        			left = null;
        		}
        	} else {
            	left.n = movedUp;
        	}
          return temp;
        } else {
        	KGPNode temp = right.n;
        	KGPNode movedUp = right.moveUp();
        	if(movedUp.compareTo(right.n) == 0) {
        		if(right.left == null && right.right == null ){
        			right = null;
        		}
        	} else {
            	right.n = movedUp;

        	}
        	return temp;
        }
       }

       public void pushUp() {
         if( top == null) {
           return;
         }
         // if n < n.top, move n up
         if(n.compareTo(top.n) < 0 ) {
           KGPNode temp = top.n;
           top.n = n;
           n = temp;
           top.pushUp();            
         }
       }
      public DepthAndNode findEmptySlot(int depth) {
          DepthAndNode leftDandN = null;
          DepthAndNode rightDandN = null;
          
          if(right == null || left == null) {
             return new DepthAndNode( depth, this);
          } 
          leftDandN = left.findEmptySlot(depth + 1);
          rightDandN = right.findEmptySlot( depth + 1);
          // equal subtrees or left shorter
          if( leftDandN.depth <= rightDandN.depth) {
             return leftDandN;
          } else { 
             return rightDandN;
          } 
        }
       
   }
 
  public class KGPNode implements Comparable{
	    int sweat;
	    private BitSet visited;
	    int currentTree;
	    String visitedString;
	    int numTrees;
	    
	     public KGPNode(int sweat, int currentTree, BitSet visited, int numTrees){
	      this.sweat = sweat;
	      this.currentTree = currentTree;
	      this.visited = visited;
	      setVisitedString();
	      this.numTrees = numTrees;
	     }
	     
	     private void setVisitedString() {
		      visitedString = visited.toString();
		      visitedString = visitedString.substring(1,visitedString.length() - 1);
	     }
	     
	     public boolean allVisited() {
	      return visited.cardinality() == numTrees;
	     }
	     
	     public boolean getBit( int bit) {
	        return visited.get(bit);
	     }
	     
	     public BitSet getVisited() {
	      return visited;
	     }
	    
	    public void setBit(int bit) {
	      visited.set(bit);
	      setVisitedString();
	    }
	    
	    public int compareTo(Object node) {
	      if( sweat - ((KGPNode)node).sweat == 0 ) {
	    	  return visitedString.compareTo(((KGPNode)node).visitedString);
	      } else {
	    	  return sweat - ((KGPNode)node).sweat;	    
	      }
	    }
	    } // node


  public static void main(String args[]) throws Exception {
	  BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	  int T = Integer.parseInt(bufferedReader.readLine());;
	  for(int i = 0; i < T; i++) {
		  int N = Integer.parseInt(bufferedReader.readLine());
    	int[][] trees = new int[N][2];
    	StringTokenizer st;
    	for(int j = 0; j < N; j++ ) {
        	 st = new StringTokenizer(bufferedReader.readLine());
             trees[j][0] = Integer.parseInt(st.nextToken());
    		trees[j][1] = Integer.parseInt(st.nextToken());
    	}
    	int[] studentsRoom = new int[2];
    	st = new StringTokenizer(bufferedReader.readLine());
    	studentsRoom[0] = Integer.parseInt(st.nextToken());
    	studentsRoom[1] = Integer.parseInt(st.nextToken());
    	int[] classroom = new int[2];
    	st = new StringTokenizer(bufferedReader.readLine());
        classroom[0] = Integer.parseInt(st.nextToken());
    	classroom[1] = Integer.parseInt(st.nextToken());
    	Main kgpSummers = new Main(studentsRoom, classroom, trees);
        kgpSummers.search();
        System.out.println(kgpSummers.minSweat);
            	
    }
      
    //  order is 4,0(5); 4, -1(1); 6, -2 (5); 7, -1(2); 8,0(2); 8,2(4); 9,2(1) = 20 
    // (1,7) order is 4,0(5); {8,1} 17; {9,2}(2) = 24
    //System.out.printf("Trees visited = %s.  Max tree size = %d", kgpSummers.minSweatRooms.toString(), kgpSummers.t.maxNodes);
    
    }

    
    // binary tree with min at the top, children are order independent
    //    5
    //  10
    // add 3
    //    3
    //  10  5
    // remove 3
    //    5
    //  10
    
    // need a moveUp and moveDown operation
    
    
 
    public class BinarySearchTree {
    BinaryTreeNode top = null;
    int maxNodes = 0;
    int curNodes = 0;
    public BinarySearchTree(int numTrees) {
    }
    
    public boolean isEmpty() {
      return top == null;
    }
    
    // find first leaf with empty slot
    // find empty leaf
     //  5
     // goes to 
     //  5
     // 10
     //   5
     //  10  
     // goes to 
     //    5
     //  10  9
     
     //    5
     //  10  9
     // goes to
     //    5
     //  10  9
     // 12
     
      
     
    
    // find empty leaf
    // recursively do moveUp if node < up
    public void add(KGPNode newKGPNode ) {
      BinaryTreeNode newNode = new BinaryTreeNode(newKGPNode);
      // BinaryTreeNode top = top;
      if(top == null) {
        top = newNode;
      } else {
        // find bottom
         BinaryTreeNode n = top.findEmptySlot(0).n;
         newNode.top = n;
         if(n.left == null) {
            n.left = newNode;
         } else {
            n.right = newNode;
         }        
        // now moveUp
        newNode.pushUp();
      }
      curNodes++;
      if( maxNodes < curNodes) {
    	  maxNodes = curNodes;
      }
     }
    
    // get top node
    // move up left or right child 
    public KGPNode remove() {
      BinaryTreeNode n = top;
      if( n == null) {
        return null;
      } else {
    	  curNodes--;
        KGPNode nodeMovedUp = top.moveUp();
        if( top.n == nodeMovedUp ) {
          top = null;
          return nodeMovedUp;
        }
        KGPNode temp = top.n;
        top.n = nodeMovedUp;       
        return temp;
      }
     }
  }
}