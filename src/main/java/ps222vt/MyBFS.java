package ps222vt;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;

/**
 * Implements  the BFS<E> interface.
 * 
 * @author Prasannjeet Singh
 *
 * @param <E>
 */
public class MyBFS<E> implements BFS<E>{
	
	HelpClass<E> helpclass = new HelpClass<E>();

	/*
	 * This method directly uses the rootBFS method previously defined in
	 * HelpCLass.java. More details about the algorithm can be found in the file
	 * HelpClass.java.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see ps222vt.BFS#bfs(ps222vt.DirectedGraph, ps222vt.Node)
	 */
	@Override
	public List<Node<E>> bfs(DirectedGraph<E> graph, Node<E> root) {
		if (graph == null || root == null) throw new RuntimeException ("Received a NULL input");
		return helpclass.rootBFS(root);
	}

	/*
	 * Method follows the same approach of entirely copying the graph, applying BFS
	 * on a random node, then deleting all the nodes that were returned, and later
	 * applying BFS again to the remaining graph. This goes on until the copied
	 * graph is completely empty. We later concatenate all the result into one
	 * single list, then get the original nodes from the original graph by using
	 * returnOriginalNodes method in help class. We return this original node list.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see ps222vt.BFS#bfs(ps222vt.DirectedGraph)
	 */
	@Override
	public List<Node<E>> bfs(DirectedGraph<E> graph) {
		if (graph == null) throw new RuntimeException ("Received a NULL input");
		DirectedGraph<E> copyGraph = helpclass.copyDG(graph);
		List<Node<E>> returnList = new ArrayList<Node<E>>();
		while (true) {
			if (copyGraph.nodeCount() == 0)
				break;
			List<Node<E>> totalNodes = helpclass.nodesAsList(copyGraph);
			//Collections.shuffle(totalNodes);
			returnList.addAll(helpclass.rootBFS(totalNodes.get(0)));
			for (Node<E> returnNodes : returnList) {
				helpclass.removeNodeForModified(copyGraph, returnNodes.item());
			}
		}
		returnList = helpclass.returnOriginalNodes(returnList, graph);
		int bfsCount = 0;
		int attachCount = 0;
		for (Node<E> attachNum : returnList) {
			attachNum.num = attachCount++;
			((MyNode<E>) attachNum).bfs_count = bfsCount++;
		}
		return returnList;
	}
}
