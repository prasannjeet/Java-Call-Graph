package ps222vt.test;

import ps222vt.DirectedGraph;
import ps222vt.Node;

import java.util.Iterator;
import java.util.Random;


/*
 * A generator of graphs implementing the interface da4234.DirectedGraph.
 * It can be used in both testing and benchmarking. You initialize the
 * graph generator by providing one instance of a class implementing the 
 * DirectedGraph interface. All graphs generated will then be instances of 
 * that class.
 */

public class GraphGenerator {
	private Class<DirectedGraph<Integer>> graph_class;
	private Integer[] data = new Integer[50000];	
	private DirectedGraph<Integer> small_acyclic;
	private DirectedGraph<Integer> small_cyclic;
	private DirectedGraph<Integer> cyclic;
	private DirectedGraph<Integer> two_parts;
	private DirectedGraph<Integer> disconnected;
	
	public GraphGenerator(DirectedGraph<Integer> dg) {
		graph_class = (Class<DirectedGraph<Integer>>)dg.getClass();
		System.out.println("Used graph class in GraphGenerator: "+graph_class.getName());
		
		/* Initialize key data */	
		for (int i=0;i<data.length;i++) data[i] = new Integer(i);
		
		/* Setup default graphs */
		build_small_cyclic();
		build_small_acyclic();
		build_two_parts();
		build_cyclic();
		build_disconnected();
	}
	
	public DirectedGraph<Integer> getSmallAcyclic() {
		return copyOf(small_acyclic);
	}
	
	public DirectedGraph<Integer> getSmallCyclic() {
		return copyOf(small_cyclic);
	}
	
	public DirectedGraph<Integer> getTwoParts() {
		return copyOf(two_parts);
	}
	
	public DirectedGraph<Integer> getCyclic() {
		return copyOf(cyclic);
	}
	
	
	public DirectedGraph<Integer> getDisconnected() {
		return copyOf(disconnected);
	}
	
	public Integer[] getUsedItems() {
		return data.clone();
	}
	
	public DirectedGraph<Integer> getComplete(int nodeCount) {
		DirectedGraph<Integer> dg = createEmptyGraph();
		for (int i=0;i<nodeCount;i++)
			for (int j=0;j<nodeCount;j++)
				addEdge(dg,i,j);
		return dg;
	}
	
	public DirectedGraph<Integer> getRandom(int nodeCount, int edges_per_node) {
		if (nodeCount/10 < edges_per_node) {
			int max = nodeCount * nodeCount;
			int edges = edges_per_node*nodeCount;
			double probability = (double) edges/max;
			//System.out.println(probability);
			
			return getRandom(nodeCount,probability);
		}
		else {
			Random random = new Random();			
			DirectedGraph<Integer> dg = createEmptyGraph();
			
			Node<Integer>[] nodes = new Node[nodeCount];
			for (int i=0;i<nodeCount;i++) {
				Node<Integer> n = dg.addNodeFor(data[i]);
				nodes[i] = n;
			}
			
			for (int i=0;i<nodeCount;i++) {
				Node<Integer> n = nodes[i];
				Integer ni = n.item();
				for (int j=0;j<edges_per_node;j++) {
					int si = random.nextInt(nodeCount);
					Integer ti = data[si];
					dg.addEdgeFor(ni, ti);
				}
			}
			return dg;
		}
	}
	
	public DirectedGraph<Integer> getClustered(int nodeCount, int edges_per_node, int clusters) {
		if (nodeCount % clusters != 0)
			throw new IllegalArgumentException("nodeCount%clusters must be 0!");
		if (nodeCount/10 < edges_per_node) {
			int max = nodeCount * nodeCount;
			int edges = edges_per_node*nodeCount;
			double probability = (double) edges/max;
			//System.out.println(probability);
			
			return getRandom(nodeCount,probability);
		} else {
			Random random = new Random();			
			DirectedGraph<Integer> dg = createEmptyGraph();
			
			Node<Integer>[] nodes = new Node[nodeCount];
			for (int i=0;i<nodeCount;i++) {
				Node<Integer> n = dg.addNodeFor(data[i]);
				nodes[i] = n;
			}
			
			for (int i=0;i<nodeCount;i++) {
				int cluster = i / (nodeCount/clusters); 
				Node<Integer> n = nodes[i];
				Integer ni = n.item();
				for (int j=0;j<edges_per_node;j++) {
					int si = random.nextInt(nodeCount / clusters);
					Integer ti = data[si + (nodeCount/clusters)*cluster];
					dg.addEdgeFor(ni, ti);
				}
			}
			return dg;
		}
	}

	public DirectedGraph<Integer> getRandom(int nodeCount, double probability) {
		if (probability < 0.0 || probability > 1.0) {
			String msg = "Probability must be in range [0.0, 1.0]";
			throw new RuntimeException(msg);
		}
		
		Random random = new Random();
		
		DirectedGraph<Integer> dg = createEmptyGraph();
		for (int i=0;i<nodeCount;i++)
			for (int j=0;j<nodeCount;j++) {
				double r = random.nextDouble();
				if (r < probability)
					addEdge(dg,i,j);
			}
		return dg;
	}
	
	private int count; 
	public DirectedGraph<Integer> getBinaryTree(int depth) {
		DirectedGraph<Integer> dg = createEmptyGraph();
		
		count = 0;
		int p = count++;
		dg.addNodeFor(keys(p));				
		addChildren(dg,p,depth);
		
		return dg;
	}
	private void addChildren(DirectedGraph<Integer> dg,int parent, int depth) {
		if (depth > 0) {
			depth--;
			
			int k1 =count++;
			addEdge(dg,parent,k1);
			
			int k2 =count++;
			addEdge(dg,parent,k2);
			
			// Visit children
			addChildren(dg,k1,depth);
			addChildren(dg,k2,depth);

			depth++;
		}
	}
	
	public DirectedGraph<Integer> getRandomReachable(int depth, int edges_per_node) {
		Random random = new Random();
		DirectedGraph<Integer> dg = getBinaryTree(depth);
		int nodeCount = dg.nodeCount();
		
		for (int i=0;i<nodeCount;i++) {
			Integer ni = data[i];
			for (int j=0;j<edges_per_node;j++) {
				int si = 1+random.nextInt(nodeCount-1);
				Integer ti = data[si];
				dg.addEdgeFor(ni, ti);
			}
		}
		//System.out.println(dg.nodeCount()+"\t"+dg.edgeCount());
		return dg;
	}
	
	public DirectedGraph<Integer> createEmptyGraph() {
		DirectedGraph<Integer> g = null;
		try {
			g = graph_class.newInstance();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(e);
		}
		return g;
	}

	
	private void build_small_cyclic() {
		DirectedGraph<Integer> dg = createEmptyGraph();
		addEdge(dg,0,1);addEdge(dg,0,2);
		addEdge(dg,1,3);addEdge(dg,2,3);
		addEdge(dg,2,2); // reflexive
		addEdge(dg,3,1); //cycle
		addEdge(dg,3,4);addEdge(dg,4,5);addEdge(dg,5,3); //cycle
		addEdge(dg,4,6); //leaf
		
		small_cyclic = dg;
	}
	
	private void build_small_acyclic() {
		DirectedGraph<Integer> dg = createEmptyGraph();
		addEdge(dg,0,1);addEdge(dg,0,2);
		addEdge(dg,1,3);addEdge(dg,2,3);
		addEdge(dg,3,4);addEdge(dg,4,5);addEdge(dg,3,5); 
		addEdge(dg,4,6); //leaf
		
		small_acyclic = dg;
	}
	
	private void build_two_parts() {
		DirectedGraph<Integer> dg = createEmptyGraph();
		
		/* cyclic part */
		addEdge(dg,0,1);addEdge(dg,0,2);
		addEdge(dg,1,3);addEdge(dg,2,3);
		addEdge(dg,2,2); // reflexive
		addEdge(dg,3,1); //cycle
		addEdge(dg,3,4);addEdge(dg,4,5);addEdge(dg,5,3); //cycle
		addEdge(dg,4,6); //leaf
		
		/* acyclic part */
		addEdge(dg,10,11);addEdge(dg,10,12);
		addEdge(dg,11,13);addEdge(dg,12,13);
		addEdge(dg,13,14);addEdge(dg,14,15);addEdge(dg,13,15); 
		addEdge(dg,14,16); //leaf
		
		two_parts = dg;
	}
	
	private void build_cyclic() {
		DirectedGraph<Integer> dg = createEmptyGraph();

		addEdge(dg,0,2); //addEdge(dg,2,0);
		addEdge(dg,0,1);
		addEdge(dg,1,3);addEdge(dg,2,3);
		addEdge(dg,3,1); //cycle
		addEdge(dg,3,4);addEdge(dg,4,5);addEdge(dg,5,3); //cycle
		addEdge(dg,5,6);addEdge(dg,6,7);addEdge(dg,7,6);addEdge(dg,6,8);addEdge(dg,7,8);
		addEdge(dg,4,9); addEdge(dg,9,10); addEdge(dg,10,11); addEdge(dg,11,9); 
		
		cyclic = dg;
	}
	
	private void build_disconnected() {
		DirectedGraph<Integer> dg = createEmptyGraph();

		addEdge(dg,0,1); addEdge(dg,1,2);addEdge(dg,2,3);addEdge(dg,3,0);
		addEdge(dg,4,5);addEdge(dg,6,5);
		addEdge(dg,7,8);
		dg.addNodeFor(keys(9));
		
		disconnected = dg;
	}
	
	
	/*
	 *   Private Utility Methods
	 */
	
	private DirectedGraph<Integer> copyOf(DirectedGraph<Integer> dg) {
		DirectedGraph<Integer> copy = createEmptyGraph();
		
		// Add nodes
		for (Node<Integer> n : dg) 
			copy.addNodeFor(n.item());
		
		// Add edges
		for (Node<Integer> n : dg) {
			Iterator<Node<Integer>> it = n.succsOf();
			while (it.hasNext())
				copy.addEdgeFor(n.item(),it.next().item());
		}
			
		return copy;
	}
	private Integer keys(int i) {		
		if (i<=data.length) 
			return data[i];
		else
			throw new RuntimeException("Index out of range");
	}
	
	private void addEdge(DirectedGraph<Integer> dg, int from, int to) {
		Integer src = keys(from);
		Integer tgt = keys(to);
		dg.addEdgeFor(src, tgt);
	}


}
