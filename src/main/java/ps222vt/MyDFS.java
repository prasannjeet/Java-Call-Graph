package ps222vt;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * Implements the interface "DFS" that was provided. It uses an instance of the
 * class HelpClass<E> defined separately in the projects. It contains a few
 * help-methods that are used by this class as well as many other classes. It
 * was done to avoid overcrowding the main files.
 * 
 * @author Prasannjeet Singh
 *
 * @param <E>
 */
public class MyDFS<E> implements DFS<E> {
	HelpClass<E> helpclass = new HelpClass<E>();

	/*
	 * Used a non-recursive method to implement DFS. Following method only returns
	 * the nodes which are reachable from the node "root". The process is described
	 * in brief below:
	 * 
	 * 		1. Prints the root, pushes the first successor to the stack, if not in the
	 * 		   output already. If already in the output push the second successor and
	 * 		   so on.
	 * 		2. Peeks the stack, makes the element root and repeat the first step.
	 * 		3. Pops the element if unable to find the successor and repeat step 2.
	 * 		4. If unable to find any successor and the stack is empty, break the loop.
	 * 		5. Return the printed value.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see ps222vt.DFS#dfs(ps222vt.DirectedGraph, ps222vt.Node)
	 */
	@Override
	public List<Node<E>> dfs(DirectedGraph<E> graph, Node<E> root) {
		if (graph == null || root == null) throw new RuntimeException ("Received a NULL input");
		List<Node<E>> returnList = new ArrayList<Node<E>>();
		HashSet<Node<E>> returnSet = new HashSet<Node<E>>();
		Stack<Node<E>> nodeStack = new Stack<Node<E>>();
		nodeStack.push(root);
		returnList.add(root);
		
		/* Adding the final dfs list both in nodeStack as well as returnList, returnList
		 * will be returned and nodeStack is used to search for elements in the runtime,
		 * as searching for the elements in an ArrayList takes significantly more time
		 * as compared to a HashSet.
		 */
		returnSet.add(root);
		Node<E> rootNode = root, loopVar = null;
		while (!nodeStack.isEmpty()) {
			boolean flag = false;
			Iterator<Node<E>> sucIterator = rootNode.succsOf();
			while (sucIterator.hasNext()) {
				loopVar = sucIterator.next();
				if (returnSet.contains(loopVar))//Searching for loopVar in HashSet "returnSet".
					continue;
				else {
					nodeStack.push(loopVar);
					returnList.add(loopVar);
					returnSet.add(loopVar);
					flag = true;
					break;
				}
			}
			if (flag == true) {
				rootNode = loopVar;
			}
			if (flag == false) {
				nodeStack.pop();
				if (nodeStack.isEmpty())
					continue;
				rootNode = nodeStack.peek();
			}
		}
		//Attaching a dfs number to each node below.
		int dfsCount = 0;
		int attachCount = 0;
		for (Node<E> attachNum : returnList) {
			attachNum.num = attachCount++;
			((MyNode<E>) attachNum).dfs_count = dfsCount++;
		}
		return returnList;
	}

	/*
	 * This methods comes as a little modification to the above method List<Node<E>>
	 * dfs(DirectedGraph<E> graph, Node<E> root). Firstly, it randomly picks up a 
	 * node from the directed graph; after finishing DFS on that node, it picks up
	 * another node randomly and if it has not been returned as a part of any
	 * previous DFS, it performs DFS on it. This time while performing the DFS, the
	 * algorithm ignores all those nodes which were previously included in any 
	 * previous instances of DFS results. This goes on until all the nodes of the graph
	 * have been covered. 
	 * 
	 * All the dfs results are eventually concatenated into a single list and returned.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see ps222vt.DFS#dfs(ps222vt.DirectedGraph)
	 */
	@Override
	public List<Node<E>> dfs(DirectedGraph<E> graph) {
		if (graph == null) throw new RuntimeException ("Received a NULL input");
		Iterator<Node<E>> headIterator = graph.iterator();
		List<Node<E>> nodeList = new ArrayList<Node<E>>(), returnList = new ArrayList<Node<E>>();
		HashSet<Node<E>> returnSet = new HashSet<Node<E>>(), stackSet = new HashSet<Node<E>>();
		while (headIterator.hasNext())
			nodeList.add(headIterator.next());
		//Collections.shuffle(nodeList); // So that every time, a random item is picked up.
		if (nodeList.isEmpty())
			throw new RuntimeException("Empty Graph!");
		Iterator<Node<E>> nodeListIterator = nodeList.iterator();
		Stack<Node<E>> nodeStack = new Stack<Node<E>>();
		Node<E> rootNode = new MyNode<E>(null), loopVar = null;
		while (true) {
			boolean flag = false;
			Iterator<Node<E>> sucIterator = rootNode.succsOf();
			while (sucIterator.hasNext()) {
				loopVar = sucIterator.next();
				if (returnSet.contains(loopVar) || stackSet.contains(loopVar)) {
					continue;
				} else {
					nodeStack.push(loopVar);
					stackSet.add(loopVar);
					returnList.add(loopVar);
					returnSet.add(loopVar);
					flag = true;
					break;
				}
			}
			if (flag == true) {
				rootNode = loopVar;
				continue;
			}
			if (flag == false) {
				if (!nodeStack.isEmpty()) {
					
					stackSet.remove(nodeStack.pop());
				}
				if (nodeStack.isEmpty()) {
					
					if (nodeListIterator.hasNext()) {
						Node<E> vTempVar = nodeListIterator.next();
						if (returnSet.contains(vTempVar) || stackSet.contains(vTempVar))
							continue;
						rootNode = nodeStack.push(vTempVar);
						stackSet.add(vTempVar);
						returnList.add(rootNode);
						returnSet.add(rootNode);
						continue;
					} else
						break;
				}
				rootNode = nodeStack.peek();
			}
		}
		int dfsCount = 0;
		int attachCount = 0;
		for (Node<E> attachNum : returnList) {
			attachNum.num = attachCount++;
			((MyNode<E>) attachNum).dfs_count = dfsCount++;
		}
		return returnList;
	}

	/*
	 * The following method is almost same as List<Node<E>> dfs(DirectedGraph<E>
	 * graph, Node<E> root). Only difference: instead of adding an element to output
	 * while pushing it in the stack, it adds the element to output while popping it
	 * out of the stack, which in turn converts the algorithm to a post order.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see ps222vt.DFS#postOrder(ps222vt.DirectedGraph, ps222vt.Node)
	 */
	@Override
	public List<Node<E>> postOrder(DirectedGraph<E> g, Node<E> root) {
		if (g == null || root == null) throw new RuntimeException ("Received a NULL input");
		List<Node<E>> returnList = new ArrayList<Node<E>>();
		Stack<Node<E>> nodeStack = new Stack<Node<E>>();
		nodeStack.push(root);
		Node<E> rootNode = root, loopVar = null;
		while (!nodeStack.isEmpty()) {
			boolean receivedANewNode = false;
			Iterator<Node<E>> sucIterator = rootNode.succsOf();
			while (sucIterator.hasNext()) {
				loopVar = sucIterator.next();
				if (returnList.contains(loopVar) || nodeStack.contains(loopVar))
					continue;
				else {
					nodeStack.push(loopVar);
					receivedANewNode = true;
					break;
				}
			}
			if (receivedANewNode == true) {
				rootNode = loopVar;
			}
			if (receivedANewNode == false) {
				returnList.add(nodeStack.pop());
				if (nodeStack.isEmpty())
					continue;
				rootNode = nodeStack.peek();
			}
		}
		int poCount = 0;
		int attachCount = 0;
		for (Node<E> attachNum : returnList) {
			attachNum.num = attachCount++;
			((MyNode<E>) attachNum).po_count = poCount++;
		}
		return returnList;
	}

	/*
	 * This algorithm uses a slightly different approach to find the post-order or a
	 * directed graph, and return ALL the nodes. It uses double the memory than the
	 * methods seen above, but at the same time, is relatively easier to implement.
	 * Following is a brief outline:
	 * 
	 * 		1. Entirely deep-clones the given directed graph to a new graph, as ob1=ob2
	 * 		   will just reference the same object to another variable. This is done 
	 * 		   because in the process of running this algorithm, we will be modifying the
	 * 		   directed graph.
	 * 		2. Pick up a random node from the copied directed graph and perform a post-
	 * 		   order using the above method List<Node<E>> postOrder(DirectedGraph<E> g, 
	 * 		   Node<E> root).
	 * 		3. Now modify the copied graph by removing all the nodes that were returned
	 * 		   the post-order performed above.
	 * 		4. Repeat step 2 with the new copied graph after removing all nodes. Do it
	 * 		   until the copied graph becomes empty.
	 * 		5. All the post-order results should be concatenated to one List.
	 *      6. Before returning the list, cross-reference these nodes from the original
	 *         directed graph, and return the list with original nodes.
	 *         
	 *         Note: For entirely copying the directed graph a method copyDG is used, 
	 *         and for cross-referencing the final list with original directed graph, and
	 *         returning the original list is done by a method returnOriginalNodes, both
	 *         of which is defined in HelpClass<E>. 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see ps222vt.DFS#postOrder(ps222vt.DirectedGraph)
	 */
	@Override
	public List<Node<E>> postOrder(DirectedGraph<E> g) {
		if (g == null)
			throw new RuntimeException("Received a NULL input");
		if (g.nodeCount() == 0)
			throw new RuntimeException("Received an EMPTY graph");
		DirectedGraph<E> copyGraph = helpclass.copyDG(g); // Deep clones the directed graph
		List<Node<E>> allNodes = helpclass.nodesAsList(copyGraph), returnList = new ArrayList<Node<E>>();
		List<Node<E>> randomNodes = allNodes;
		//Collections.shuffle(randomNodes);
		while (true) {
			returnList.addAll(postOrder(copyGraph, randomNodes.get(0))); // Perform post order
			for (Node<E> nodesToBeRemoved : returnList) {
				helpclass.removeNodeForModified(copyGraph, nodesToBeRemoved.item()); // remove nodes
			}
			randomNodes = helpclass.nodesAsList(copyGraph);
			if (copyGraph.nodeCount() == 0)
				break;
		}
		returnList = helpclass.returnOriginalNodes(returnList, g); // Returns nodes of the original graph.
		int poCount = 0;
		int attachCount = 0;
		for (Node<E> attachNum : returnList) {
			attachNum.num = attachCount++;
			((MyNode<E>) attachNum).po_count = poCount++;
		}
		return returnList;
	}

	/*
	 * Working of this method is briefly described below:
	 * 
	 * 		1. Performs a Post Order in the given directed graph. 
	 * 		2. If attach_dfs_number is false, attaches the output with a post-order 
	 * 		   number. 
	 * 		3. If attach_dfs_number is true, attaches the output with a dfs in-order
	 * 		   number.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see ps222vt.DFS#postOrder(ps222vt.DirectedGraph, boolean)
	 */
	@Override
	public List<Node<E>> postOrder(DirectedGraph<E> g, boolean attach_dfs_number) {
		if (g == null) throw new RuntimeException ("Received a NULL input");
		List<Node<E>> returnList = postOrder(g);
		if (attach_dfs_number) {
			List<Node<E>> tempDfsList = dfs(g);
			for (Node<E> returnNode: returnList) {
				for (Node<E> tempNode: tempDfsList) {
					if (((MyNode<E>)returnNode).equals((MyNode<E>)tempNode)) {
						((MyNode<E>)returnNode).dfs_count = ((MyNode<E>)tempNode).dfs_count;
						returnNode.num = tempNode.num;;
						break;
					}
				}
			}
		}
		return returnList;
	}
	
	/*
	 * This method uses DFS to find out whether the directed graph is cyclic or not.
	 * For that, it uses a help method called as nodeDFS() which is defined in
	 * HelpClass.java, that method will further be explained in it's own definition.
	 * For now, it performs dfs on a node that is passed as a parameter, and checks
	 * a backedge using three sets that is WhiteSet, GraySet and BlackSet (explained
	 * in detail later). The method returns a null value if it finds out a cycle,
	 * otherwise, it keeps running and distributing nodes in the three sets.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see ps222vt.DFS#isCyclic(ps222vt.DirectedGraph)
	 */
	@Override
	public boolean isCyclic(DirectedGraph<E> graph) {
		if (graph == null) throw new RuntimeException ("Received a NULL input");
		Set<Node<E>> WhiteSet = new HashSet<Node<E>>();
		Set<Node<E>> GraySet = new HashSet<Node<E>>();
		Set<Node<E>> BlackSet = new HashSet<Node<E>>();
		Map<Integer, Set<Node<E>>> threeSets = new HashMap<Integer, Set<Node<E>>>();
		boolean isCyclic = false;
		threeSets.put(1, WhiteSet);
		threeSets.put(2, GraySet);
		threeSets.put(3, BlackSet);
		Iterator<Node<E>> nodeIterator1 = graph.iterator();
		while (nodeIterator1.hasNext()) {
			WhiteSet.add(nodeIterator1.next());
		}
		Iterator<Node<E>> nodeIterator2 = graph.iterator();
		while (nodeIterator2.hasNext()) {
			threeSets = helpclass.nodeDFS(nodeIterator2.next(), threeSets);
			if (threeSets == null) {
				isCyclic = true;
				break;
			}
		}
		return (isCyclic);
	}

	/*
	 * This method is inspired by the above method
	 * "List<Node<E>> postOrder(DirectedGraph<E> g)" in the basic structure. Below
	 * is described briefly how it works:
	 * 
	 * 		1. Deep clone the graph.
	 * 		2. Save all the head-node in a list.
	 * 		3. Now remove all the head nodes that were extracted from the copied graph.
	 * 		4. Repeat step-2 until the copied graph becomes empty.
	 * 		5. Concatenate all the lists which we get from step 2.
	 * 		6. Now get the original nodes from original directed graph and return them.
	 * 
	 * Basically head nodes in the graph have no dependency, so they obviously be the 
	 * first ones. Now if we remove all the head nodes, in the remaining graph, the head
	 * node will now have no dependency, so we add them, and then remove them too... and
	 * go on doing this until the graph is empty!
	 * 
	 * (non-Javadoc)
	 * 
	 * @see ps222vt.DFS#topSort(ps222vt.DirectedGraph)
	 */
	@Override
	public List<Node<E>> topSort(DirectedGraph<E> graph) {
		if (graph == null) throw new RuntimeException ("Received a NULL input");
		if (this.isCyclic(graph))
			return null;
		DirectedGraph<E> copyGraph = helpclass.copyDG(graph);
		List<Node<E>> returnList = new ArrayList<Node<E>>();
		while (true) {
			if (copyGraph.headCount() == 0)
				break;
			List<Node<E>> headList = helpclass.headsAsList(copyGraph);
			returnList.addAll(headList);
			for (Node<E> eachHead : headList) {
				copyGraph.removeNodeFor(eachHead.item());
			}
		}
		returnList = helpclass.returnOriginalNodes(returnList, graph);
		int attachCount = 0;
		for (Node<E> loopNode : returnList) {
			loopNode.num = attachCount++;
		}
		return returnList;
	}
}
