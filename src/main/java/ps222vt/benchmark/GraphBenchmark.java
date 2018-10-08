/**
 * 
 */
package ps222vt.benchmark;


import ps222vt.BFS;
import ps222vt.ConnectedComponents;
import ps222vt.DFS;
import ps222vt.DirectedGraph;
import ps222vt.Node;
import ps222vt.TransitiveClosure;
import ps222vt.test.GraphGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import ps222vt.*;     // Replace with groupname.*

/**
 * @author jonasl
 *
 */
public class GraphBenchmark {
	private final GraphGenerator generator;
	private final int SIZE;	// 100 in competition
	
	public GraphBenchmark(int size) {
		 SIZE = size; 
		 generator = new GraphGenerator(new MyGraph<Integer>());
	}
	
	/* ********************************************************
	 * 			General Graph Performance
	 * The check_remove method is taken from teachers.TestDirectedGraph.
	 * I have simply removed all assertX() methods call.
	 * 
	 * ********************************************************/
	
	public double runGraphPerformance() {
		System.out.println("\n\tGeneral Graph Performance");

		long start = System.currentTimeMillis();
		DirectedGraph<Integer>[] graphs = getPerformanceGraphs();
		DirectedGraph<Integer> g;
		System.out.print("\t\tRuns: ");
		for (int i=0;i<graphs.length;i++) {
			g = graphs[i];
			check_remove(g);
			System.out.print("  "+i);
		}
	    long stop = System.currentTimeMillis();
	    double time = (double) (stop-start)/1000;
	    System.out.println("\n\tMem: "+gcMem());
	    System.out.println("\tTime: "+time);
	    return time;
	}
	
	private DirectedGraph<Integer>[] getPerformanceGraphs() {
		int sz = 15*SIZE;
		int esz = 100;
		int count = 5;
		
		DirectedGraph<Integer>[] graphs = new DirectedGraph[count];
		DirectedGraph<Integer> g;
		
		/* Add random graphs */
		System.out.println("\t\tRandom graphs: N = "+sz+" E/N = "+esz);
		System.out.print("\t\tBuilding");
		for (int i=0;i<count;i++) {
			g = generator.getRandom(sz,esz);
			graphs[i] = g;
			System.out.print("  "+i);
		}
		System.out.println("  OK!");
		
		return graphs;
	}
	
	private void check_graph(DirectedGraph<Integer> dg) {
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
				n.isTail();
			if (n.inDegree() == 0)
				heads.add(n);		
			else
				n.isHead();
		}
		
		/* check items */
		List<Integer> itms = dg.allItems();
		items.size();
		itms.containsAll(items);
		for (Integer i : itms) {
			dg.containsNodeFor(i);
			dg.getNodeFor(i);
			dg.addNodeFor(i);
		}
		
		/* check heads */
		dg.headCount();
		Iterator<Node<Integer>> it =dg.heads();
		while (it.hasNext()) {
			Node<Integer> n = it.next();
			n.isHead();
		}
		
		/* check tails */
		dg.tailCount();
		it =dg.tails();
		while (it.hasNext()) {
			Node<Integer> n = it.next();
			n.isTail();
		}
	}
	
	private void check_node(Node<Integer> n) {
		Node<Integer> p,s;
		
		int count = 0;
		Iterator<Node<Integer>> it =n.succsOf();
		while (it.hasNext()) {
			count++;
			s = it.next();
			n.hasSucc(s);
			s.hasPred(n);
			if (s == n)
				n.hasReflexiveEdges();
		}
		n.outDegree();
		
		count = 0;
		it =n.predsOf();
		while (it.hasNext()) {
			count++;
			p = it.next();
			n.hasPred(p);
			p.hasSucc(n);
			if (p == n)
				n.hasReflexiveEdges();
		}
		n.inDegree();
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
				dg.containsNodeFor(item);
			}
		}
		dg.nodeCount();
		check_graph(dg);
		
		/* Collect edges */
		int ec = dg.edgeCount();
		count = 0;
		ArrayList edges = new ArrayList();
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
			dg.containsEdgeFor(pair[0], pair[1]);
		}
		dg.edgeCount();
		check_graph(dg);
	}
	
	/* *****************************************************
	 *     DFS and BFS
	 * *****************************************************/
	
	public double runDfsBfs() {
		System.out.println("\n\tDFS and BFS Components");
				
		DFS<Integer> dfs = new MyDFS<Integer>();
		BFS<Integer> bfs = new MyBFS<Integer>();
		DirectedGraph<Integer>[] graphs = getConnectedGraphs();
		DirectedGraph<Integer> g;
		System.out.println("\t\tGarbage collection: ....  "+gcMem());
		System.out.print("\t\tRuns (x4): ");
		long start = System.currentTimeMillis();
		for (int i=0;i<graphs.length;i++) {
			g = graphs[i];
			int l1 = dfs.dfs(g).size();  // size of returned dfs list
			int l2 = dfs.dfs(g).size();
			int l3 = dfs.dfs(g).size();
			int l4 = dfs.dfs(g).size();
			int b1 = bfs.bfs(g).size();  // size of returned bfs list
			int b2 = bfs.bfs(g).size();
			int b3 = bfs.bfs(g).size();
			int b4 = bfs.bfs(g).size();
			if (l1 != b1 || l2 != b2 || l3 != b3 || l4 != b4 )
				throw new RuntimeException("Size difference in DFS and BFS results");
			System.out.print("  "+i);
		}
		long stop = System.currentTimeMillis();
	    double time = (double) (stop-start)/1000;
	    System.out.println("\n\tMem: "+mem());
	    System.out.println("\tTime: "+time);
	    return time;
	}
	
	private DirectedGraph<Integer>[] getDBFSGraphs() {
		int sz = 15*SIZE;
		int esz = 100;
		int count = 5;
		
		DirectedGraph<Integer>[] graphs = new DirectedGraph[count];
		DirectedGraph<Integer> g;
		
		/* Add random graphs */
		System.out.println("\t\tRandom graphs: N = "+sz+" E/N = "+esz);
		System.out.print("\t\tBuilding");
		for (int i=0;i<count;i++) {
			g = generator.getRandom(sz,esz);
			graphs[i] = g;
			System.out.print("  "+i);
		}
		System.out.println("  OK!");
		
		return graphs;
	}
	

	
	/* *****************************************************
	 *     Transitive Closure
	 * *****************************************************/
	public double runTransitiveClosure() {
		System.out.println("\n\tTransitive Closure");
				
		TransitiveClosure<Integer> tc = new MyTransitiveClosure<Integer>();
		DirectedGraph<Integer>[] graphs = getClosureGraphs();
		System.out.println("\t\tGarbage collection: ....  "+gcMem());
		DirectedGraph<Integer> g;
		System.out.print("\t\tRuns: ");
		long start = System.currentTimeMillis();
		for (int i=0;i<graphs.length;i++) {
			long st = System.currentTimeMillis();
			g = graphs[i];
			tc.computeClosure(g);
			System.out.print("  "+i);
		}
	    long stop = System.currentTimeMillis();
	    double time = (double) (stop-start)/1000;
	    System.out.println("\n\tMem: "+mem());
	    System.out.println("\tTime: "+time);
	    return time;
	}
	
	private DirectedGraph<Integer>[] getClosureGraphs() {
		int count = 6;
		int sz = 15*SIZE;
		int esz = 100;
		
		DirectedGraph<Integer>[] graphs = new DirectedGraph[count];
		DirectedGraph<Integer> g;
		
		/* Add dense random graphs (SCC = 1)*/
		System.out.println("\t\tRandom graphs: N = "+sz+" E/N = "+esz);
		System.out.print("\t\tBuilding");
		for (int i=0;i<graphs.length/2;i++) {
			g = generator.getRandom(sz,esz);
			//System.out.println("N: "+g.nodeCount()+"  E: "+g.edgeCount());
			graphs[i] = g;
			System.out.print("  "+i);
		}
		System.out.println("  OK!");
		
		/* Add sparse random graphs */
		sz = 40*SIZE;
		esz = 3;
		System.out.println("\t\tRandom graphs: N = "+sz+" E/N = "+esz);
		System.out.print("\t\tBuilding");
		for (int i=0;i<graphs.length/2;i++) {
			g = generator.getRandom(sz,esz);
			//System.out.println("N: "+g.nodeCount()+"  E: "+g.edgeCount());
			graphs[graphs.length/2+i] = g;
			System.out.print("  "+i);
		}
		System.out.println("  OK!");
		
		return graphs;
	}
	
	
	/* *****************************************************
	 *     Connected Components
	 * *****************************************************/
	
	public double runConnectedComponents() {
		System.out.println("\n\tConnected Components");
				
		ConnectedComponents<Integer> cc = new MyConnectedComponents<Integer>();
		DirectedGraph<Integer>[] graphs = getConnectedGraphs();
		DirectedGraph<Integer> g;
		System.out.println("\t\tGarbage collection: ....  "+gcMem());
		System.out.print("\t\tRuns (x4): ");
		long start = System.currentTimeMillis();
		for (int i=0;i<graphs.length;i++) {
			g = graphs[i];
			Collection<Collection<Node<Integer>>> cc1 = cc.computeComponents(g);
			Collection<Collection<Node<Integer>>> cc2 = cc.computeComponents(g);
			Collection<Collection<Node<Integer>>> cc3 = cc.computeComponents(g);
			Collection<Collection<Node<Integer>>> cc4 = cc.computeComponents(g);
			if (cc1 == cc2 || cc1 == cc3 || cc1 == cc4 || cc2 == cc3 || cc2 == cc4 || cc3 == cc4)
				throw new RuntimeException("Caching of results is forbidden!");
			cc1.iterator().next().size();
			cc2.iterator().next().size();
			cc3.iterator().next().size();
			cc4.iterator().next().size();
			System.out.print("  "+i);
		}
		long stop = System.currentTimeMillis();
	    double time = (double) (stop-start)/1000;
	    System.out.println("\n\tMem: "+mem());
	    System.out.println("\tTime: "+time);
	    return time;
	}
	
	private DirectedGraph<Integer>[] getConnectedGraphs() {
		int count_1 = 3;
		int count_2 = 3;
		int sz = 50*SIZE;
		int esz = 100;

		int clusters_2 = 4;
		
		DirectedGraph<Integer>[] graphs = new DirectedGraph[count_1 + count_2];
		DirectedGraph<Integer> g;
		
		/* Add random graphs */
		System.out.println("\t\tRandom graphs: N = "+sz+" E/N = "+esz);
		System.out.print("\t\tBuilding");
		for (int i=0;i<count_1;i++) {
			g = generator.getRandom(sz,esz);
			graphs[i] = g;
			System.out.print("  "+i);
		}
		for (int i=0; i < count_2; i++) {
			g = generator.getClustered(sz, esz, clusters_2);
			graphs[i+count_1] = g;
			System.out.print("  "+(i+count_1));
		}
		System.out.println("  OK!");
		
		return graphs;
	}
	
			
	/*
	 * Returns a string (e.g. 27 Mbytes) indicating the amount of 
	 * memory used by the JVM. It enforces a garbage collection 
	 * before it measures the memory.
	 * return a string containing memory size after garbage collection. 
	 */
	public String gcMem() {
	    Runtime runtime = Runtime.getRuntime();
	    long mem_size = runtime.totalMemory()-runtime.freeMemory();
	    long prev;
	    do {
	    	prev = mem_size;
	    	runtime.gc();
	    	mem_size = runtime.totalMemory()-runtime.freeMemory(); 
	    } while (prev !=mem_size);
	    return mem_size/1000000 +" Mbytes";    
	}
	
	public String mem() {
	    Runtime runtime = Runtime.getRuntime();
		long mem_size = runtime.totalMemory()-runtime.freeMemory(); 
	    return mem_size/1000000 +" Mbytes";    
	}
}
