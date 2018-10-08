
package ps222vt.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNotNull;
import ps222vt.DirectedGraph;
import ps222vt.GML;
import ps222vt.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import ps222vt.*;                 // Replace with groupname.*;

public class TestDirectedGraph  {
	
	// Run test as a standard Java application
	public static void main(String[] args) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out)); 
		junit.run(TestDirectedGraph.class);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static final GraphGenerator generator  = new GraphGenerator(new MyGraph());
	
	/* Is executed before every test method.*/
	@Before
	public void setUp() {
		System.out.println("Test: "+this.getClass().getName());
	}
	
	/* Is executed after every test method (not test case).*/
	@After
	public void tearDown() {}
	
	/* ********************************************************
	 *    My Test Methods
	 * ********************************************************/

	@Test
	public void testCreateAdd() {
		DirectedGraph<Integer> g = generator.createEmptyGraph();		
		Integer[] i = generator.getUsedItems();
		
		/* Add nodes */
		g.addNodeFor(i[0]);
		g.addNodeFor(i[1]);
		Node<Integer> n2 = g.addNodeFor(i[2]);
		Node<Integer> n3 = g.getNodeFor(i[2]);
		assertSame(n2,n3);
		assertEquals(3,g.nodeCount());
		assertEquals(0,g.edgeCount());
		
		/* Add connecting edges */
		assertTrue(g.addEdgeFor(i[0],i[1]));
		assertTrue(g.addEdgeFor(i[0],i[2]));
		assertTrue(g.addEdgeFor(i[1],i[2]));
		assertEquals(3,g.nodeCount());
		assertEquals(3,g.edgeCount());
		
		/* Add node creating edges */
		g.addEdgeFor(i[1],i[3]);
		g.addEdgeFor(i[2],i[3]);
		assertEquals(4,g.nodeCount());
		assertEquals(5,g.edgeCount());
		
		/* Add singleton node */
		Node<Integer> n4 = g.addNodeFor(i[4]);
		assertSame(n4,g.getNodeFor(i[4]));
		assertEquals(5,g.nodeCount());
		assertEquals(5,g.edgeCount());
		
		assertEquals(2,g.headCount());
		assertEquals(2,g.tailCount());
		
		/* Add duplicated entities */
		assertSame(n2,g.addNodeFor(i[2]));
		assertSame(n4,g.addNodeFor(i[4]));
		assertFalse(g.addEdgeFor(i[1],i[3]));
		assertFalse(g.addEdgeFor(i[2],i[3]));
		assertEquals(5,g.nodeCount());
		assertEquals(5,g.edgeCount());
		
		System.out.println(g);
		
		/* Test contains */
		assertTrue(g.containsNodeFor(i[1]));
		assertTrue(g.containsNodeFor(i[4]));
		assertTrue(g.containsEdgeFor(i[1],i[3]));
		assertTrue(g.containsEdgeFor(i[2],i[3]));
		assertFalse(g.containsNodeFor(i[5]));
		assertFalse(g.containsEdgeFor(i[1],i[4]));
		assertFalse(g.containsEdgeFor(i[4],i[5]));
		assertFalse(g.containsEdgeFor(i[6],i[5]));
		
		/* Verify contains doesn't change graph */
		assertEquals(5,g.nodeCount());
		assertFalse(g.containsNodeFor(i[5]));
		assertFalse(g.containsNodeFor(i[6]));
		assertFalse(g.containsEdgeFor(i[6],i[5]));
		
		/* Consistency tests */
		DirectedGraph<Integer> dg = generator.getSmallCyclic();
		check_graph(dg);
		
		dg = generator.getComplete(10);
		check_graph(dg);
		
		dg = generator.getRandom(100,0.1);
		check_graph(dg);
	}
	
	@Test
	public void testRemove() {
		DirectedGraph<Integer> cyclic = generator.getSmallCyclic();
		Integer[] i = generator.getUsedItems();
		assertEquals(7,cyclic.nodeCount());
		assertEquals(10,cyclic.edgeCount());
		
		Node<Integer> src = cyclic.getNodeFor(i[3]);
		Node<Integer> tgt = cyclic.getNodeFor(i[1]);
		assertTrue(cyclic.removeEdgeFor(src.item(),tgt.item()));
		assertEquals(7,cyclic.nodeCount());
		assertEquals(9,cyclic.edgeCount());
		
		cyclic.removeNodeFor(i[5]);
		assertEquals(6,cyclic.nodeCount());
		assertEquals(7,cyclic.edgeCount());

		cyclic.removeNodeFor(i[6]);
		assertEquals(5,cyclic.nodeCount());
		assertEquals(6,cyclic.edgeCount());
		
		cyclic.removeNodeFor(i[0]);
		assertEquals(4,cyclic.nodeCount());
		assertEquals(4,cyclic.edgeCount());
		
		/* Remove non-existing items */
		cyclic.removeEdgeFor(i[1],i[5]);
		cyclic.removeEdgeFor(i[1],i[9]);
		cyclic.removeEdgeFor(i[10],i[9]);
		assertEquals(4,cyclic.nodeCount());
		assertEquals(4,cyclic.edgeCount());
		
		/* Consistency tests */
		DirectedGraph<Integer> dg = generator.getSmallCyclic();
		check_remove(dg);
		
		dg = generator.getComplete(10);
		check_remove(dg);
		
		dg = generator.getRandom(100,0.1);
		check_remove(dg);
	}
	
	@Test
	public void testExceptions() {
		DirectedGraph<Integer> dg = generator.getSmallCyclic();
		Integer err = new Integer(-1);	
		
		/* Test null item exceptions */
		boolean b = false;
		try { dg.addNodeFor(null); }
		catch(Exception e) {b =true;}
		assertTrue(b);
	
		b = false;
		try { dg.getNodeFor(null); }
		catch(Exception e) {b =true;}
		assertTrue(b);
		
		b = false;
		try { dg.removeNodeFor(null); }
		catch(Exception e) {b =true;}
		assertTrue(b);
		
		b = false;
		try { dg.containsNodeFor(null); }
		catch(Exception e) {b =true;}
		assertTrue(b);
		
		b = false;
		try { dg.addEdgeFor(null,err); }
		catch(Exception e) {
			b =true;
			assertFalse(dg.containsNodeFor(err));
		}
		assertTrue(b);
		
		b = false;
		try { dg.containsEdgeFor(err,null); }
		catch(Exception e) {
			b =true;
			assertFalse(dg.containsNodeFor(err));
		}
		assertTrue(b);
		
		b = false;
		try { dg.removeEdgeFor(err,null); }
		catch(Exception e) {
			b =true;
			assertFalse(dg.containsNodeFor(err));
		}
		assertTrue(b);
		
		/* Test non-existing exceptions */	
		b = false;
		try { dg.getNodeFor(err); }
		catch(Exception e) {b =true;}
		assertTrue(b);
		
		b = false;
		try { dg.removeNodeFor(err); }
		catch(Exception e) {b =true;}
		assertTrue(b);
		
		
	}
	
	@Test
	public void testIterator() {
		DirectedGraph<Integer> cyclic = generator.getSmallCyclic();
		Iterator<Integer> it = cyclic.allItems().iterator();
		int count = 0;
		while (it.hasNext()) {
			count++;
			assertTrue(cyclic.containsNodeFor(it.next()));
		}
		assertEquals(count,cyclic.nodeCount());
		
		Iterator<Node<Integer>> itt = cyclic.iterator();
		count = 0;
		while (itt.hasNext()) {
			count++;
			assertTrue(cyclic.containsNodeFor(itt.next().item()));
		}
		assertEquals(count,cyclic.nodeCount());
		
		/* Empty iterators */
		DirectedGraph<Integer> empty = generator.createEmptyGraph();
		
		itt = empty.iterator();
		assertNotNull(itt);
		for (count = 0;itt.hasNext();) count++;
		assertEquals(0,count);
		
		itt = empty.heads();
		assertNotNull(itt);
		for (count = 0;itt.hasNext();) count++;
		assertEquals(0,count);
		
		itt = empty.tails();
		assertNotNull(itt);
		for (count = 0;itt.hasNext();) count++;
		assertEquals(0,count);
		
		List<Integer> items = empty.allItems();
		assertNotNull(items);
		assertEquals(0,items.size());
	}
	
	@Test  // Simple test to verify that it also works for string
	public void testWithStrings() {
		String[] s = {"A","B","C","D","E","F","G","H"};
		DirectedGraph<String> g = new MyGraph<String>();
		
		/* Add nodes */
		g.addNodeFor(s[0]);
		g.addNodeFor(s[1]);
		Node<String> n2 = g.addNodeFor(s[2]);
		Node<String> n3 = g.getNodeFor(s[2]);
		assertSame(n2,n3);
		assertEquals(3,g.nodeCount());
		assertEquals(0,g.edgeCount());
		
		/* Add connecting edges */
		assertTrue(g.addEdgeFor(s[0],s[1]));
		assertTrue(g.addEdgeFor(s[0],s[2]));
		assertTrue(g.addEdgeFor(s[1],s[2]));
		assertEquals(3,g.nodeCount());
		assertEquals(3,g.edgeCount());
		
		/* Add node creating edges */
		g.addEdgeFor(s[1],s[3]);
		g.addEdgeFor(s[2],s[3]);
		assertEquals(4,g.nodeCount());
		assertEquals(5,g.edgeCount());
		
		/* Add singleton node */
		Node<String> n4 = g.addNodeFor(s[4]);
		assertSame(n4,g.getNodeFor(s[4]));
		assertEquals(5,g.nodeCount());
		assertEquals(5,g.edgeCount());
		
		assertEquals(2,g.headCount());
		assertEquals(2,g.tailCount());
		
		/* Add duplicated entities */
		assertSame(n2,g.addNodeFor(s[2]));
		assertSame(n4,g.addNodeFor(s[4]));
		assertFalse(g.addEdgeFor(s[1],s[3]));
		assertFalse(g.addEdgeFor(s[2],s[3]));
		assertEquals(5,g.nodeCount());
		assertEquals(5,g.edgeCount());
		
		System.out.println(g);
		
		/* Test contains */
		assertTrue(g.containsNodeFor(s[1]));
		assertTrue(g.containsNodeFor(s[4]));
		assertTrue(g.containsEdgeFor(s[1],s[3]));
		assertTrue(g.containsEdgeFor(s[2],s[3]));
		assertFalse(g.containsNodeFor(s[5]));
		assertFalse(g.containsEdgeFor(s[1],s[4]));
		assertFalse(g.containsEdgeFor(s[4],s[5]));
		assertFalse(g.containsEdgeFor(s[6],s[5]));
		
		Iterator<Node<String>> it = g.iterator();
		int count = 0;
		while (it.hasNext()) {
			count++;
			assertTrue(g.containsNodeFor(it.next().item()));
		}
		assertEquals(count,g.nodeCount());
		
		for (Node<String> n : g) {
			System.out.print(n.item()+" ");
		}
		System.out.println(); // break line
		
	}
	
	@Test
	public void testGML() throws Exception {
		DirectedGraph<Integer> cyclic = generator.getSmallCyclic();
		GML<Integer> gml = new MyGML<Integer>(cyclic);
		gml.dumpGML();
	}

	
	/* ********************************************************
	 * 			Private help methods
	 * ********************************************************/
	public static  void check_graph(DirectedGraph<Integer> dg) {
		ArrayList<Node<Integer>> nodes = new ArrayList<Node<Integer>>();
		HashSet<Integer> items = new HashSet<Integer>();
		ArrayList<Node<Integer>> heads = new ArrayList<Node<Integer>>();
		ArrayList<Node<Integer>> tails = new ArrayList<Node<Integer>>();
		
		for (Node<Integer> n : dg) {
			nodes.add(n);
			items.add(n.item());
			check_node(n);
			
			/* head and tails */
			if (n.outDegree() == 0)
				tails.add(n);
			else
				assertFalse(n.isTail());
			if (n.inDegree() == 0)
				heads.add(n);		
			else
				assertFalse(n.isHead());
		}
		
		/* check items */
		List<Integer> itms = dg.allItems();
		assertEquals(itms.size(),items.size());
		assertTrue(itms.containsAll(items));
		for (Integer i : itms) {
			assertTrue(dg.containsNodeFor(i));
			assertTrue(null != dg.getNodeFor(i));
			assertTrue(null != dg.addNodeFor(i));
		}
		
		/* check heads */
		assertEquals(heads.size(),dg.headCount());
		Iterator<Node<Integer>> it =dg.heads();
		while (it.hasNext()) {
			Node<Integer> n = it.next();
			assertTrue(heads.contains(n));
			assertTrue(n.isHead());
		}
		
		/* check tails */
		assertEquals(tails.size(),dg.tailCount());
		it =dg.tails();
		while (it.hasNext()) {
			Node<Integer> n = it.next();
			assertTrue(tails.contains(n));
			assertTrue(n.isTail());
		}
	}
	
	private static void check_node(Node<Integer> n) {
		Node<Integer> p,s;
		
		int count = 0;
		Iterator<Node<Integer>> it =n.succsOf();
		while (it.hasNext()) {
			count++;
			s = it.next();
			assertTrue(n.hasSucc(s));
			assertTrue(s.hasPred(n));
			if (s == n)
				assertTrue(n.hasReflexiveEdges());
		}
		assertEquals(count,n.outDegree());
		
		count = 0;
		it =n.predsOf();
		while (it.hasNext()) {
			count++;
			p = it.next();
			assertTrue(n.hasPred(p));
			assertTrue(p.hasSucc(n));
			if (p == n)
				assertTrue(n.hasReflexiveEdges());
		}
		assertEquals(count,n.inDegree());
	}
	
	private void check_remove(DirectedGraph<Integer> dg) {
		final int N = 5; // Remove every N:th node
		final int E = 3; // Remove every E:th edge
		
		List<Integer> items = dg.allItems();
		int count = 0, nc = dg.nodeCount();
		for (int i=1;i<items.size();i++) {
			if (i%N == 0) {
				count++;
				Integer item = items.get(i);
				dg.removeNodeFor(item);
				assertFalse(dg.containsNodeFor(item));
			}
		}
		assertEquals(nc-count,dg.nodeCount());
		check_graph(dg);
		
		/* Collect edges */
		int ec = dg.edgeCount();
		count = 0;
		ArrayList<Integer[]> edges = new ArrayList<Integer[]>();
		for (Node<Integer> n : dg) {
			Iterator<Node<Integer>> it =n.succsOf();
			while (it.hasNext()) {
				count++;
				Node<Integer> s = it.next();
				if (count%E == 0) {
					Integer[] pair = new Integer[]{n.item(),s.item()};
					edges.add(pair);
				}
			}
		}
		
		/* Remove collected edges */
		for (Object o : edges) {
			Integer[] pair = (Integer[]) o;
			dg.removeEdgeFor(pair[0], pair[1]);
			assertFalse(dg.containsEdgeFor(pair[0], pair[1]));
		}
		assertEquals(ec-edges.size(),dg.edgeCount());
		check_graph(dg);
	}
}
