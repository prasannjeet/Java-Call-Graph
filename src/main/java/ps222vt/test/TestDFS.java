/*
*   TestDFS.java
*
*   Created 2007-dec-02, 16:22:52
*/
package ps222vt.test;

import ps222vt.BFS;
import ps222vt.DFS;
import ps222vt.DirectedGraph;
import ps222vt.Node;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;


import ps222vt.*;                      // Replace with groupname.*;
/**
 * 
 * A  weakness in this case is that many tests
 * are based on manual inspection. That is, we apply
 * algorithms on a number of well-known, predefined graphs
 * and make a manual inspection to check the correctness.
 * 
 * @author jonasl
 *
 */
public class TestDFS  {    
	// Run test as a standard Java application
	public static void main(String[] args) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out)); 
		junit.run(TestDFS.class);
	}
	
	/* ************************************************
	 *      Test suite setup
	 * ************************************************/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static final GraphGenerator generator = new GraphGenerator(new MyGraph());
    
	@Before
	public void setUp() throws Exception {
		System.out.println(this.getClass().getName());
	}

	@After
	public void tearDown() {}


	/* ************************************************
	 *      Tests
	 * ************************************************/
	@Test
	public void testDFS() throws Exception {
		DirectedGraph<Integer> dg = generator.getTwoParts();
		Integer[] i = generator.getUsedItems();
		Node<Integer> root1 = dg.getNodeFor(i[0]);
		Node<Integer> root2 = dg.getNodeFor(i[10]);
		
		DFS<Integer> dfs = new MyDFS<Integer>();
		
    	/* Test cyclic part with root1 */
    	List<Node<Integer>> list = dfs.dfs(dg,root1);
    	assertEquals(list.size(),7);
    	int start = list.get(0).num;
    	for (int j=0;j<list.size();j++) {
    		Node<Integer> node =  list.get(j);
    		assertEquals(j+start,node.num);
    	}
    	assertSame(list.get(0),root1);
    	assertSame(list.get(2),dg.getNodeFor(i[3]));
    	assertTrue(list.indexOf(dg.getNodeFor(i[3]))<list.indexOf(dg.getNodeFor(i[4])));
    	assertTrue(list.indexOf(dg.getNodeFor(i[4]))<list.indexOf(dg.getNodeFor(i[5])));
    	System.out.println("\tCyclic dfs-order[0]: "+list);
    	
       	/* Test acyclic part with root2 */
    	list = dfs.dfs(dg,root2);
    	assertEquals(list.size(),7);
    	start = list.get(0).num;
    	for (int j=0;j<list.size();j++) {
    		Node<Integer> node =  list.get(j);
    		assertEquals(j+start,node.num);
    	}
    	assertSame(list.get(0),dg.getNodeFor(i[10]));
    	assertTrue(list.get(6)==dg.getNodeFor(i[11]) || list.get(6)==dg.getNodeFor(i[12]));
    	assertSame(list.get(2),dg.getNodeFor(i[13]));
    	System.out.println("\tAcyclic dfs-order[10]: "+list);
    	
    	/* Test other start nodes */
    	Node<Integer> n = dg.getNodeFor(i[4]);
    	list = dfs.dfs(dg,n);
    	assertEquals(list.size(),5);
    	
    	n = dg.getNodeFor(i[6]);
    	list = dfs.dfs(dg,n);
    	assertEquals(list.size(),1);
    	
    	n = dg.getNodeFor(i[13]);
    	list = dfs.dfs(dg,n);
    	assertEquals(list.size(),4);
    	
    	/* Test graph with two heads */
    	list = dfs.dfs(dg);
    	System.out.println("\tTwoParts dfs-order: "+list);
    	assertEquals(list.size(),dg.nodeCount());
    	start = list.get(0).num;
    	for (int j=0;j<list.size();j++) {
    		Node<Integer> node =  list.get(j);
    		assertEquals(j+start,node.num);
    	}
	}
	
	@Test
	public void testBFS() throws Exception {
		DirectedGraph<Integer> dg = generator.getTwoParts();
		Integer[] i = generator.getUsedItems();
		Node<Integer> root1 = dg.getNodeFor(i[0]);
		Node<Integer> root2 = dg.getNodeFor(i[10]);
		
		BFS<Integer> bfs = new MyBFS<Integer>();
		
    	/* Test cyclic part with root1 */
    	List<Node<Integer>> list = bfs.bfs(dg,root1);
    	assertEquals(list.size(),7);
    	int start = list.get(0).num;
    	for (int j=0;j<list.size();j++) {
    		Node<Integer> node =  list.get(j);
    		assertEquals(j+start,node.num);
    	}
    	assertSame(list.get(0),root1);
    	assertSame(list.get(3),dg.getNodeFor(i[3]));
    	assertTrue(list.indexOf(dg.getNodeFor(i[3]))<list.indexOf(dg.getNodeFor(i[4])));
    	assertTrue(list.indexOf(dg.getNodeFor(i[4]))<list.indexOf(dg.getNodeFor(i[5])));
    	System.out.println("\tCyclic bfs-order[0]: "+list);
    	
       	/* Test acyclic part with root2 */
    	list = bfs.bfs(dg,root2);
    	assertEquals(list.size(),7);
    	start = list.get(0).num;
    	for (int j=0;j<list.size();j++) {
    		Node<Integer> node =  list.get(j);
    		assertEquals(j+start,node.num);
    	}
    	assertSame(list.get(0),dg.getNodeFor(i[10]));
    	assertTrue(list.get(6)==dg.getNodeFor(i[15]) || list.get(6)==dg.getNodeFor(i[16]));
    	assertSame(list.get(3),dg.getNodeFor(i[13]));
    	System.out.println("\tAcyclic bfs-order[10]: "+list);
    	
    	/* Test other start nodes */
    	Node<Integer> n = dg.getNodeFor(i[4]);
    	list = bfs.bfs(dg,n);
    	assertEquals(list.size(),5);
    	
    	n = dg.getNodeFor(i[6]);
    	list = bfs.bfs(dg,n);
    	assertEquals(list.size(),1);
    	
    	n = dg.getNodeFor(i[13]);
    	list = bfs.bfs(dg,n);
    	assertEquals(list.size(),4);
    	
    	/* Test graph with two heads */
    	list = bfs.bfs(dg);
    	System.out.println("\tTwoParts dfs-order: "+list);
    	assertEquals(list.size(),dg.nodeCount());
    	start = list.get(0).num;
    	for (int j=0;j<list.size();j++) {
    		Node<Integer> node =  list.get(j);
    		assertEquals(j+start,node.num);
    	}
	}
	
	@Test
    public void testPostOrder() throws Exception {
    	DirectedGraph<Integer> dg = generator.getTwoParts();
    	Integer[] i = generator.getUsedItems();
    	Node<Integer> root1 = dg.getNodeFor(i[0]);
    	Node<Integer> root2 = dg.getNodeFor(i[10]);

    	DFS<Integer> dfs = new MyDFS<Integer>();		

		
    	/* Test cyclic with root */
    	List<Node<Integer>> list = dfs.postOrder(dg,root1);
    	System.out.println("\tTwoParts post-order[0]: "+list);
    	assertEquals(list.size(),7);
    	int start = list.get(0).num;
    	for (int j=0;j<list.size();j++) {
    		Node<Integer> node =  list.get(j);
    		assertEquals(j+start,node.num);
    	}
    	assertSame(list.get(6),root1);
    	assertTrue(list.get(5)==dg.getNodeFor(i[1]) || list.get(5)==dg.getNodeFor(i[2]));
    	assertTrue(list.get(0)==dg.getNodeFor(i[5]) || list.get(0)==dg.getNodeFor(i[6]) || list.get(0)==dg.getNodeFor(i[1]));
    	assertTrue(list.get(4)==dg.getNodeFor(i[3])  || list.get(4)==dg.getNodeFor(i[1]));
    	
       	/* Test acyclic with root */
    	list = dfs.postOrder(dg,root2);
    	assertEquals(list.size(),7);
    	start = list.get(0).num;
    	for (int j=0;j<list.size();j++) {
    		Node<Integer> node =  list.get(j);
    		assertEquals(j+start,node.num);
    	}
    	assertSame(list.get(6),root2);
    	assertTrue(list.get(5)==dg.getNodeFor(i[11]) || list.get(5)==dg.getNodeFor(i[12]));
    	assertTrue(list.get(0)==dg.getNodeFor(i[15]) || list.get(0)==dg.getNodeFor(i[16]));
    	assertSame(list.get(3),dg.getNodeFor(i[13]));
    	System.out.println("\tAyclic post-order[10]: "+list);
    	
    	/* Test other start nodes */
    	Node<Integer> n = dg.getNodeFor(i[4]);
    	list = dfs.postOrder(dg,n);
    	assertEquals(list.size(),5);
    	
    	n = dg.getNodeFor(i[6]);
    	list = dfs.postOrder(dg,n);
    	assertEquals(list.size(),1);
    	
    	n = dg.getNodeFor(i[13]);
    	list = dfs.postOrder(dg,n);
    	assertEquals(list.size(),4);

    	/* Test graph with no root */
    	list = dfs.postOrder(dg);
    	System.out.println("\tTwoParts post-order: "+list);
    	assertEquals(list.size(),dg.nodeCount());
    	start = list.get(0).num;
    	for (int j=0;j<list.size();j++) {
    		Node<Integer> node =  list.get(j);
    		assertEquals(j+start,node.num);
    	}
    }
    
	@Test
    public void testCyclic() throws Exception {
    	DFS<Integer> dfs = new MyDFS<Integer>();
    	
    	DirectedGraph<Integer> dg = new MyGraph<Integer>();

    	Integer i1 = new Integer(1);
    	dg.addNodeFor(i1);
    	assertFalse(dfs.isCyclic(dg));
    	
    	Integer i2 = new Integer(2);
    	dg.addEdgeFor(i1,i2);
    	assertFalse(dfs.isCyclic(dg));
    	

    	dg.addEdgeFor(i2,i2);
    	assertTrue(dfs.isCyclic(dg));
    	
    	/* Test some pre-defined graphs */
    	dg = generator.getSmallAcyclic();
    	assertFalse(dfs.isCyclic(dg));
    	
    	dg = generator.getSmallCyclic();
    	assertTrue(dfs.isCyclic(dg));
    	
    	dg = generator.getTwoParts();
    	assertTrue(dfs.isCyclic(dg));
    	
    	dg = generator.getBinaryTree(4);
    	assertFalse(dfs.isCyclic(dg));
    	
    	dg = generator.getComplete(4);
    	assertTrue(dfs.isCyclic(dg));
    	
    	/* Topological sort */
    	dg = generator.getSmallAcyclic();
    	List<Node<Integer>> sorted = dfs.topSort(dg);
    	System.out.println("\tAcyclic top-sort: "+sorted);
    	
    	/* Check basic property */
    	for (Node<Integer> src : sorted) {
			int from = sorted.indexOf(src);
			Iterator<Node<Integer>> it = src.succsOf();
			while (it.hasNext()) {
				Node<Integer> tgt = it.next();
				int to = sorted.indexOf(tgt);
				assertTrue(from <= to);
			}		
		}
    	
    	dg = generator.getBinaryTree(4);
    	sorted = dfs.topSort(dg);
    	
    	/* Check basic property */
    	for (Node<Integer> src : sorted) {
			int from = sorted.indexOf(src);
			Iterator<Node<Integer>> it = src.succsOf();
			while (it.hasNext()) {
				Node<Integer> tgt = it.next();
				int to = sorted.indexOf(tgt);
				assertTrue(from <= to);
			}		
		}
    }
 
}
