package ps222vt;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Implements the TransitiveClosure<E> interface given in the question
 * statement.
 * 
 * @author Prasannjeet Singh
 *
 * @param <E>
 */
public class MyTransitiveClosure<E> implements TransitiveClosure<E> {
	/*
	 * This method does exactly what was told in the lecture:
	 * 		1.	for each n that belongs to N do
	 *		2.	List l = dfs(n)
	 *		3.	save map entry (n; l)
	 *		4.	end for
	 * 
	 * (non-Javadoc)
	 * 
	 * @see ps222vt.TransitiveClosure#computeClosure(ps222vt.DirectedGraph)
	 */
	@Override
	public Map<Node<E>, Collection<Node<E>>> computeClosure(DirectedGraph<E> dg) {
		MyDFS<E> dfsObject = new MyDFS<E>();
		Map<Node<E>, Collection<Node<E>>> returnMap = new HashMap<Node<E>, Collection<Node<E>>>();
		if (dg == null)
			throw new RuntimeException("Received a NULL input");
		Iterator<Node<E>> nodeIterator = dg.iterator();
		while (nodeIterator.hasNext()) { // for each n that belongs to N do
			Node<E> tempNode = nodeIterator.next();
			List<Node<E>> dfsList = dfsObject.dfs(dg, tempNode); // List l = dfs(n)
			returnMap.put(tempNode, dfsList); // save map entry (n; l)
		} // end for
		return returnMap;
	}
}