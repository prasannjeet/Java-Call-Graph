/*
*   DirectedGraph.java
*   Author: Jonas Lundberg
*   Created: 2007-nov-04, 23:50:41
*/
package ps222vt;

import java.util.Iterator;
import java.util.List;

/**
 * A directed graph interface. Each node (<tt>Node</tt>)
 * represents a unique <tt>item</tt> of generic type <tt>E</tt> and
 * two nodes representing the same <tt>item</tt> can not be added to the graph.
 * (The second attempt is silently ignored.) Two items <tt>i1</tt> and <tt>i2</tt>
 * are the same if <tt>i1.equals(i2) == true</tt> and <tt>i1.hashCode() == i2.hashCode()</tt>. 
 * </p>
 * Furthermore, multiple edges between two nodes is not possible (silently ignored).
 * </p>
 *
 * @author jonasl
 */
public interface DirectedGraph<E> extends Iterable<Node<E>> {
	/**
	 * Adds a node representing <tt>item</tt> if not added before.
	 * Exception is thrown if <tt>item</tt> is null. It returns the
	 * node representing <tt>item</tt> (new or previously constructed).
	 * @param item, 
	 * @return Node representing <tt>item</tt>
	 */
	public Node<E> addNodeFor(E item);
	
	/**
	 * Returns the node representing <tt>item</tt>.
	 * Exception is thrown if <tt>item</tt> is null or if no
	 * node representing <tt>item</tt> is found.
	 * @param item
	 * @return Node representing <tt>item</tt>
	 */
	public Node<E> getNodeFor(E item);
	
	/**
	 * Adds an edge between the nodes represented by <tt>from</tt>
	 * and <tt>to</tt>  if not added before. The nodes representing 
	 * <tt>from</tt> and <tt>to</tt> are added if not added before.
	 * Exception is thrown if <tt>from</tt> or <tt>to</tt> is null. 
	 * It returns <tt>true</tt> if edge not added before, otherwise <tt>false</tt>.
	 * @param from, source node
	 * @param to, target node
	 * @return <tt>true</tt> if edge not added before, otherwise <tt>false</tt>.
	 */
	public boolean addEdgeFor(E from, E to);
	
	
	/**
	 * Returns <tt>true</tt> if the node representing <tt>item</tt> is 
	 * contained in the graph, otherwise <tt>false</tt>.
	 * Exception is thrown if <tt>item</tt> is null. 
	 * @param item, node to be checked.
	 */
	public boolean containsNodeFor(E item);
	
	/**
	 * Returns the number of nodes in the graph. 
	 * @return number of nodes
	 */	
	public int nodeCount();
	
	/**
	 * Returns an iterator over all nodes in the graph. 
	 * @return graph nodes iterator
	 */	
	public Iterator<Node<E>> iterator();
	
	/**
	 * Returns an iterator over all nodes with no in-edges. 
	 * @return heads iterator
	 */
	public Iterator<Node<E>> heads();
	
	/**
	 * The number of nodes with no in-edges. 
	 * @return number of head nodes.
	 */
	public int headCount();
	
	/**
	 * Returns an iterator over all nodes with no out-edges. 
	 * @return tails iterator
	 */
	public Iterator<Node<E>> tails();
	
	/**
	 * The number of nodes with no out-edges. 
	 * @return number of head nodes.
	 */
	public int tailCount();
	
	/**
	 * Returns a list over all node items currently used in the graph. 
	 * @return list of items
	 */
	public List<E> allItems();
	
	/**
	 * Returns the number of graph edges. 
	 * @return edge count
	 */
	public int edgeCount();
	

	/**
	 * Removes the node represented by <tt>item</item> and 
	 * all its connecting edges. Exception is thrown if <tt>item</tt> 
	 * is null  or if no node representing <tt>item</tt> is found. 
	 * 
	 * @param item, node to be removed.
	 */
	public void removeNodeFor(E item);
	
	/**
	 * Returns <tt>true</tt> if an edge between the nodes represented 
	 * by <tt>from</tt> and <tt>to</tt> is added to the graph. 
	 * Exception is thrown if <tt>from</tt> or <tt>to</tt> is null. 
	 * @param from, source node item
	 * @param to, target node item
	 * @return <tt>true</tt> if edge in graph, otherwise <tt>false</tt>.
	 */
	public boolean containsEdgeFor(E from, E to);
	
	
	
	/**
	 * Removes the edge between the nodes represented 
	 * by <tt>from</tt> and <tt>to</tt> if it exist.
	 * Returns <tt>true</tt> if an edge between the nodes represented 
	 * by <tt>from</tt> and <tt>to</tt>  is found and successfully removed. 
	 * Exception is thrown if <tt>from</tt> or <tt>to</tt> is null. 
	 * @param from, source node item
	 * @param to, target node item
	 * @return <tt>true</tt> if edge in graph and successfully removed, otherwise <tt>false</tt>.
	 */
	public boolean removeEdgeFor(E from, E to) ;
	
	/**
	 * A textual representation of the graph content (nodes and edges) constructed
	 * by applying <tt>toString()</tt> on the nodes.
	 * 
	 */
	public String toString();

}
