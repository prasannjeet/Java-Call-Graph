package ps222vt;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements the interface ConnectedComponents which is given in the
 * question statement. To make the question a little easier, we do a lot of
 * manipulation to the given graph, and then get the connected components. To do
 * this, we again use the copyDFS() method to copy a graph, which is discussed
 * in detail in this method: "postOrder(DirectedGraph<E> g)". More details on
 * how these are used is described in the method itself.
 * 
 * @author Prasannjeet Singh
 *
 * @param <E>
 */
public class MyConnectedComponents<E> implements ConnectedComponents<E> {
	HelpClass<E> helpclass = new HelpClass<E>();
	MyDFS<E> helpDFS = new MyDFS<E>();

	/* 
	 * Manipulating the directed graph itself makes this question fairly easy to 
	 * solve. Below are the steps followed in the algorithm:
	 * 
	 * 		1. Deep-clone the graph, let's call it the copied graph.
	 * 		2. In the copied graph, make all the predecessors of every node, their
	 * 		   successors as well. In a way, by doing this we have sort-of created
	 * 		   an un-directed graph.
	 * 		3. Now perform a Depth First Search on any random node of the copied
	 * 		   graph, and save it in a collection. This collection will also be
	 * 		   added to the Collection<Collection> array. Since the graph now is
	 * 		   un-directed, a complete connected component will be returned,
	 * 		   irrespective of which node we start from.
	 * 		4. Remove all the nodes from the copied graph that were obtained from
	 * 		   step 3.
	 * 		5. Now run step 3 again on the updated copied graph.
	 * 	 	6. Continue this until the copied graph has no more nodes left.
	 * 		7. As always, the resultant collections should be cross-referenced
	 * 		   from the original directed graph and the collection with original 
	 * 		   nodes must be returned.
	 * 
	 * (non-Javadoc)
	 * @see ps222vt.ConnectedComponents#computeComponents(ps222vt.DirectedGraph)
	 */
	@Override
	public Collection<Collection<Node<E>>> computeComponents(DirectedGraph<E> dg) {
		if (dg == null) throw new RuntimeException ("Received a NULL input");
		Collection<Collection<Node<E>>> returnDoubleList = new LinkedList<Collection<Node<E>>>();
		DirectedGraph<E> copyGraph = helpclass.copyDG(dg);
		List<Node<E>> addEdges = helpclass.nodesAsList(copyGraph);
		for (Node<E> tempEdgeNode : addEdges) {
			List<Node<E>> tempPred = helpclass.predAsList(tempEdgeNode);
			for (Node<E> eachPred : tempPred) {
				copyGraph.addEdgeFor(tempEdgeNode.item(), eachPred.item());
			}
		}
		while (true) {
			if (copyGraph.nodeCount() == 0)
				break;
			List<Node<E>> nodesLoop = helpclass.nodesAsList(copyGraph);
			//Collections.shuffle(nodesLoop); //So that we pick a random node.
			List<Node<E>> tempDFS = helpDFS.dfs(copyGraph, nodesLoop.get(0));
			for (Node<E> eachDFSItem : tempDFS) {
				copyGraph.removeNodeFor(eachDFSItem.item());
			}
			returnDoubleList.add((ArrayList<Node<E>>) tempDFS);
		}
		for (Collection<Node<E>> theArrayList : returnDoubleList) {
			theArrayList = helpclass.returnOriginalNodes((List<Node<E>>) theArrayList, dg);
		}
		return returnDoubleList;
	}
}
