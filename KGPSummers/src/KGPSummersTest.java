import static org.junit.Assert.*;

import java.util.BitSet;

import org.junit.Test;

public class KGPSummersTest {

	@Test
	public void testSearchSimple() {
		int[] studentsRoom = {-1,1};
	    int[] classroom = {1,-1};
	    int[][] trees = {{0,0}};
	    KGPSummers summer = new KGPSummers(studentsRoom, classroom, trees);
	    summer.search();
	    assertEquals("Search on 1 used tree sweat is ", 4, summer.minSweat);
	}

	@Test
	public void testSearchSimpleUseNoTrees() {
		int[] studentsRoom = {-1,1};
	    int[] classroom = {1,-1};
	    int[][] trees = {{2,2}};
	    KGPSummers summer = new KGPSummers(studentsRoom, classroom, trees);
	    summer.search();
	    assertEquals("Search on 1 non used tree is ", 8, summer.minSweat);
	}

	@Test
	public void testSample6TreeData() {
		int [] studentsRoom = {2,1};
	    int [] classroom = {9,2};
	    int[][] trees = {{8,2},{4,0}, {8,0}, {4, -1 }, {7,-1}, {6,-2}};
	    KGPSummers kgpSummers = new KGPSummers(studentsRoom, classroom, trees);
	    kgpSummers.search();
	    assertEquals("Search on 6 nodes ", 20, kgpSummers.minSweat);
	    assertEquals("Trees ", "{0, 1, 2, 3, 4, 5}", kgpSummers.minSweatRooms.toString());
	    
	    
	}
	@Test
	public void testIterationComplex() {
		int [] studentsRoom = {2,1};
	    int [] classroom = {9,2};
	    int[][] trees = {{8,2},{4,0}, {7,0}};
	    //int[][] trees = {{8,2},{4,0}, {8,0}, {4, -1 }, {7,-1}, {6,-2}};
	    KGPSummers kgpSummers = new KGPSummers(studentsRoom, classroom, trees);
	    // minDist = 50
	    kgpSummers.iterateOnTree();
	    // {4,0}(5) then {7,0}(26)then {8,2}(37)
	    // should be in order in tree
	    assertEquals("Tree should be deep", 3, kgpSummers.t.curNodes);
	    assertEquals("Tree should be room #", 1, kgpSummers.t.top.n.currentTree);
	    assertEquals("Tree minSweatRooms Trees ", "{}", kgpSummers.minSweatRooms.toString());
	    assertEquals("Top Trees ", "1", kgpSummers.t.top.n.visitedString);
	    assertEquals("Left tree should be room #", 0, kgpSummers.t.top.left.n.currentTree);
	    assertEquals("Left Trees ", "0", kgpSummers.t.top.left.n.visitedString);
	    assertEquals("Right tree should be room #", 2, kgpSummers.t.top.right.n.currentTree);
	    assertEquals("Trees ", "2", kgpSummers.t.top.right.n.visitedString);
	    kgpSummers.iterateOnTree();
	    // min dist from {4,0}(5) is 34, {7,0}(26) is 34, 
	    // should have added 1,0({4,0}, {8,2}(25));
	    // 1, 2({4,0}, {7,0}(14))
	    // should be 5 nodes in tree, {1,2}(14), {1,0}(25)
	    // minDist is 34, minSweatRooms = {1}
	    assertEquals("Tree should be depth ", 4, kgpSummers.t.curNodes);
	    
	    assertEquals("Tree should be room #", 2, kgpSummers.t.top.n.currentTree);    
	    assertEquals("MinSweat Trees ", "{1}", kgpSummers.minSweatRooms.toString());
	    assertEquals("MinSweat ", 34, kgpSummers.minSweat);
	    assertEquals("Top Node sweat ", 14, kgpSummers.t.top.n.sweat);
	    assertEquals("Top Node visited ", "1, 2", kgpSummers.t.top.n.visitedString);
	    kgpSummers.iterateOnTree();
	    // min sweat from {1,2,class}(14) is 22
	    // {1,2,0} is 14 + 5 = 19 keep and push on should be on top, with 1,0({4,0}, {8,2}(25)) after
	    assertEquals("Tree should be depth ", 4, kgpSummers.t.curNodes);
	    assertEquals("Tree should be room #", 0, kgpSummers.t.top.n.currentTree);    
	    assertEquals("Trees ", "{1, 2}", kgpSummers.minSweatRooms.toString());
	    assertEquals("MinSweat ", 22, kgpSummers.minSweat);
	    assertEquals("Tree should be depth ", 4, kgpSummers.t.curNodes);
	    kgpSummers.iterateOnTree();
	    // min sweat from {1,2,0,class}(19) is 20.  All visited.  stack should have 4 item of 1,0({4,0}, {8,2}(25) still on.
	    assertEquals("Tree should be depth ", 3, kgpSummers.t.curNodes);
	    assertEquals("MinSweat ", 20, kgpSummers.minSweat);
	    assertEquals("Trees ", "{0, 1, 2}", kgpSummers.minSweatRooms.toString());
	    assertEquals("Tree should be room #", 2, kgpSummers.t.top.n.currentTree);    
	    kgpSummers.iterateOnTree();
	    // pull 1 item of 1,0({4,0}, {8,2}(25) and it's > 20 so throw awa
	    assertEquals("Tree should be depth ", 2, kgpSummers.t.curNodes);
	    kgpSummers.iterateOnTree();
	    assertEquals("Tree should be depth ", 1, kgpSummers.t.curNodes);
	    assertFalse("Tree should not be empty", kgpSummers.t.isEmpty());
	    assertNotNull("Tree top ", kgpSummers.t);
	    kgpSummers.iterateOnTree();
	    assertEquals("Tree should be depth ", 0, kgpSummers.t.curNodes);
	    assertTrue("Tree should be empty", kgpSummers.t.isEmpty());
	    assertNull("Tree top ", kgpSummers.t);
	    assertEquals("MinSweat ", 20, kgpSummers.minSweat);
	    assertEquals("Trees ", "{0, 1, 2}", kgpSummers.minSweatRooms.toString());
	 // -> throw away node 1 {8,2} (38)
	    // throw away 2, 0({7,0}, {8,2})
	    // throw away 2, 1({7,0}, {4,0})
	    
	}
	
	@Test
	public void testSearch3NodesUse2x() {
		int [] studentsRoom = {2,1};
	    int [] classroom = {9,2};
	    int[][] trees = {{8,0},{4,0}, {8,2}, {10,5}};
	    // Sweat should be {4,0}(5) + {8,0}(16) + {8,2}(4) + {9,2} (1) = 26
	    KGPSummers kgpSummers = new KGPSummers(studentsRoom, classroom, trees);
	    kgpSummers.search();
	    // should be in order in tree
	    assertEquals("Trees ", "{0, 1}", kgpSummers.minSweatRooms.toString());
	    assertEquals("Total sweat ", 26, kgpSummers.minSweat);
	}
	
	@Test
	public void testSearch5NodesUse3() {
		int [] studentsRoom = {2,1};
	    int [] classroom = {9,2};
	    int[][] trees = {{8,0},{4,0}, {8,2}, {10,5}, {8,1}};
	    // Sweat should be {4,0}(5) + {8,0}(16) + {8,1}(1) + {8,2}(1) + {9,2} (1) = 24
	    // Sweat could be {4,0} (5) + {8,1}(17) + {9,2} (2) = 24
	    KGPSummers kgpSummers = new KGPSummers(studentsRoom, classroom, trees);
	    kgpSummers.search();
	    // should be in order in tree
	    // assertEquals("Trees ", "{1, 0, 4, 3}", kgpSummers.minSweatRooms.toString());
	    assertEquals("Trees ", "{1, 4}", kgpSummers.minSweatRooms.toString());
	    assertEquals("Total sweat ", 24, kgpSummers.minSweat);
	}

	
	@Test
	public void testNewKGPNode() {
		int N = 1;
		int[] studentsRoom = {-1,1};
	    int[] classroom = {1,-1};
	    int[][] trees = {{0,0}};
	    KGPSummers kgpSummers = new KGPSummers(studentsRoom, classroom, trees);
		KGPSummers.KGPNode n = kgpSummers.new KGPNode(0, 0, new BitSet(N), N);
		assertEquals("KGPNode no bit set string ", "", n.visitedString);
		BitSet bs = new BitSet(trees.length);
		bs.set(1);
		n = kgpSummers.new KGPNode(0, 0, bs, N);
		assertEquals("KGPNode 1 bit set string ", "1", n.visitedString);
		
	    
	}
	@Test
	public void test1NodeInTree() {
		int N = 1;
		int[] studentsRoom = {-1,1};
	    int[] classroom = {1,-1};
	    int[][] trees = {{0,0}};
	    KGPSummers kgpSummers = new KGPSummers(studentsRoom, classroom, trees);
		KGPSummers.BinarySearchTree t = kgpSummers.new BinarySearchTree(N);
		KGPSummers.KGPNode n = kgpSummers.new KGPNode(0, 0, new BitSet(N), N);
		t.add(n);
	    n = t.remove();
	    assertEquals("removed node val should = 0", n.sweat, 0);
	    assertEquals("Tree should be empty", true, t.isEmpty());
		
	}
	
	@Test
	public void test2NodesSameBitSet() {
		int N = 1;
		int[] studentsRoom = {-1,1};
	    int[] classroom = {1,-1};
	    int[][] trees = {{0,0}};
	    KGPSummers kgpSummers = new KGPSummers(studentsRoom, classroom, trees);
		KGPSummers.BinarySearchTree t = kgpSummers.new BinarySearchTree(N);
		KGPSummers.KGPNode n = kgpSummers.new KGPNode(0, 0, new BitSet(N), N);
	    t.add(n);
	    assertEquals("top right node ", null, t.top.right);
	    assertEquals("top left node ", null, t.top.left);
	    n = kgpSummers.new KGPNode(1, 0, new BitSet(N), N);	 
	    t.add(n);
	    assertEquals("top node val is 0", 0, t.top.n.sweat);
	    assertEquals("No right node", null, t.top.right);
	    assertEquals("left node val is 1", 1, t.top.left.n.sweat);
	    assertEquals("left node top is top node", t.top, t.top.left.top);
	    assertEquals("No left node's right node", null, t.top.left.right);
	    assertEquals("No left node's left node", null, t.top.left.left);
	    n = t.remove();
	    assertEquals("top right node after remove", null, t.top.right);
	    assertEquals("top left node after remove", null, t.top.left);
	    assertEquals("top node sweat after remove ", 1, t.top.n.sweat);
	    assertEquals("first removed node val ", 0, n.sweat);
	    assertEquals("Tree should not be empty", false, t.isEmpty());
	    n = t.remove();
	    assertEquals("second removed node val ", 1, n.sweat);
	    assertEquals("Tree should be empty", true, t.isEmpty());
		
	}
	
	@Test
	public void test3NodesSameBitSet() {
		int N = 1;
		int[] studentsRoom = {-1,1};
	    int[] classroom = {1,-1};
	    int[][] trees = {{0,0}};
	    KGPSummers kgpSummers = new KGPSummers(studentsRoom, classroom, trees);
		KGPSummers.BinarySearchTree t = kgpSummers.new BinarySearchTree(N);
		KGPSummers.KGPNode n = kgpSummers.new KGPNode(0, 0, new BitSet(N), N);
	    t.add(n);
	    assertEquals("top right node ", null, t.top.right);
	    assertEquals("top left node ", null, t.top.left);
	    n = kgpSummers.new KGPNode(1, 0, new BitSet(N), N);	 
	    t.add(n);
	    assertEquals("top node val is 0", 0, t.top.n.sweat);
	    assertEquals("No right node", null, t.top.right);
	    assertEquals("left node val is 1", 1, t.top.left.n.sweat);
	    assertEquals("left node top is top node", t.top, t.top.left.top);
	    assertEquals("No left node's right node", null, t.top.left.right);
	    assertEquals("No left node's left node", null, t.top.left.left);
	    n = kgpSummers.new KGPNode(2, 0, new BitSet(N), N);	 
	    t.add(n);
	    assertEquals("top node val is ", 0, t.top.n.sweat);
	    assertTrue("right node not null",  t.top.right != null);
	    assertEquals("left node val is ", 1, t.top.left.n.sweat);
	    assertEquals("left node top is top node", t.top, t.top.left.top);
	    assertEquals("right node val is ", 2, t.top.right.n.sweat);
	    assertEquals("right node top is top node", t.top, t.top.right.top);
	    n = t.remove();
	    assertNull("top left node after remove", t.top.left);
	    assertTrue("top right node after remove not null", t.top.right != null);
	    assertEquals("top node sweat after remove ", 1, t.top.n.sweat);
	    assertEquals("first removed node val ", 0, n.sweat);
	    assertFalse("Tree should not be empty", t.isEmpty());
	    n = t.remove();
	    assertEquals("second removed node val ", 1, n.sweat);
	    assertFalse("Tree should not be empty", t.isEmpty());
	    n = t.remove();
	    assertEquals("third removed node val ", 2, n.sweat);
	    assertTrue("Tree should be empty", t.isEmpty());
	}
	
	@Test
	public void test4NodesSameBitSet() {
		int N = 1;
		int[] studentsRoom = {-1,1};
	    int[] classroom = {1,-1};
	    int[][] trees = {{0,0}};
	    KGPSummers kgpSummers = new KGPSummers(studentsRoom, classroom, trees);
		KGPSummers.BinarySearchTree t = kgpSummers.new BinarySearchTree(N);
		KGPSummers.KGPNode n = kgpSummers.new KGPNode(0, 0, new BitSet(N), N);
	    t.add(n);
	    assertEquals("top right node ", null, t.top.right);
	    assertEquals("top left node ", null, t.top.left);
	    n = kgpSummers.new KGPNode(1, 0, new BitSet(N), N);	 
	    t.add(n);
	    assertEquals("top node val is 0", 0, t.top.n.sweat);
	    assertEquals("No right node", null, t.top.right);
	    assertEquals("left node val is 1", 1, t.top.left.n.sweat);
	    assertEquals("left node top is top node", t.top, t.top.left.top);
	    assertEquals("No left node's right node", null, t.top.left.right);
	    assertEquals("No left node's left node", null, t.top.left.left);
	    n = kgpSummers.new KGPNode(2, 0, new BitSet(N), N);	 
	    t.add(n);
	    assertEquals("top node val is ", 0, t.top.n.sweat);
	    assertTrue("right node not null",  t.top.right != null);
	    assertEquals("left node val is ", 1, t.top.left.n.sweat);
	    assertEquals("left node top is top node", t.top, t.top.left.top);
	    assertEquals("right node val is ", 2, t.top.right.n.sweat);
	    assertEquals("right node top is top node", t.top, t.top.right.top);
	    n = kgpSummers.new KGPNode(3, 0, new BitSet(N), N);	 
	    t.add(n);
	    assertEquals("top node val is ", 0, t.top.n.sweat);
	    assertTrue("right node not null",  t.top.right != null);
	    assertEquals("left node val is ", 1, t.top.left.n.sweat);
	    assertEquals("left node top is top node", t.top, t.top.left.top);
	    assertEquals("right node val is ", 2, t.top.right.n.sweat);
	    assertEquals("right node top is top node", t.top, t.top.right.top);
	    assertEquals("left left node val is ", 3, t.top.left.left.n.sweat);
	    assertEquals("left left node top is top left node", t.top.left, t.top.left.left.top);
	    n = t.remove();
	    assertEquals("Removed ", 0, n.sweat);
	    assertEquals("top node val is ", 1, t.top.n.sweat);
	    assertTrue("right node not null",  t.top.right != null);
	    assertEquals("left node val is ", 3, t.top.left.n.sweat);
	    assertEquals("left node top is top node", t.top, t.top.left.top);
	    assertEquals("right node val is ", 2, t.top.right.n.sweat);
	    assertEquals("right node top is top node", t.top, t.top.right.top);
	    n = t.remove();
	    assertEquals("Removed ", 1, n.sweat);
	    assertNotNull("top left node after remove", t.top.left);
	    assertNull("top right node after remove", t.top.right);
	    assertEquals("top node sweat after remove ", 2, t.top.n.sweat);
	    assertFalse("Tree should not be empty", t.isEmpty());
	    n = t.remove();
	    assertEquals("second removed node val ", 2, n.sweat);
	    assertFalse("Tree should not be empty", t.isEmpty());
	    n = t.remove();
	    assertEquals("third removed node val ", 3, n.sweat);
	    assertTrue("Tree should be empty", t.isEmpty());
		
	}


	@Test
	public void test2NodesDifferentBitSet() {
		int N = 1;
		int[] studentsRoom = {-1,1};
	    int[] classroom = {1,-1};
	    int[][] trees = {{0,0}};
	    KGPSummers kgpSummers = new KGPSummers(studentsRoom, classroom, trees);
		KGPSummers.BinarySearchTree t = kgpSummers.new BinarySearchTree(N);
		KGPSummers.KGPNode n = kgpSummers.new KGPNode(1, 0, new BitSet(N), N);
	    t.add(n);
	    assertEquals("top right node ", null, t.top.right);
	    assertEquals("top left node ", null, t.top.left);
	    assertEquals("top node val ", 1, t.top.n.sweat);
	    BitSet bs = new BitSet(N);
	    bs.set(1);
	    n = kgpSummers.new KGPNode(0, 1, bs, N);
	    // value 1 should be on top because the bitset for rooms visited is less
	    t.add(n);
	    assertEquals("top node val ", 0, t.top.n.sweat);
	    assertEquals("No right node", null, t.top.right);
	    assertEquals("left node val ", 1, t.top.left.n.sweat);
	    assertEquals("left node top is top node", t.top, t.top.left.top);
	    assertEquals("No left node's right node", null, t.top.left.right);
	    assertEquals("No left node's left node", null, t.top.left.left);
	    n = t.remove();
	    assertEquals("top right node after remove", null, t.top.right);
	    assertEquals("top left node after remove", null, t.top.left);
	    assertEquals("top node sweat after remove ", 1, t.top.n.sweat);
	    assertEquals("first removed node val ", 0, n.sweat);
	    assertEquals("Tree should not be empty", false, t.isEmpty());
	    n = t.remove();
	    assertEquals("second removed node val ", 1, n.sweat);
	    assertEquals("Tree should be empty", true, t.isEmpty());
		
	}


}
