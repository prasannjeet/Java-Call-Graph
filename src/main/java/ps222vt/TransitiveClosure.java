/**
 * 
 */
package ps222vt;

import java.util.Collection;
import java.util.Map;

/**
 * An algorithm that computes the transitive closure for the graph.
 * The result is a <tt>java.util.Map</tt> that associates each node n to 
 * a collection of nodes that are reachable from n. Notice: by definition, 
 * every node can reach itself.
 * 
 * 
 * 
 * 
 * @author jonasl
 *
 */
public interface TransitiveClosure<E> {

	/**
	 * Computes the transitive closure for the graph.
	 *
	 */
	public Map<Node<E>,Collection<Node<E>>> computeClosure(DirectedGraph<E> dg);
}
