/**
 * 
 */
package ps222vt;

import java.util.Collection;

/**
 * Two nodes a and b are directly connected if their exist an edge (a,b) 
 * or an edge (b,a). Two nodes a and k are connected if there exist a sequence
 * of nodes [a,b,c,d, ... j,k] such that [a,b], [b,c], [c,d], [d,e], ..., [j,k]
 * are all directly connected.
 * <p/>
 * Problem: find a partitioning of the graph nodes such that two nodes belongs to the 
 * same partitioning if and only if they are connected.
 * </p>
 * The result is a collection of node collections.
 *  
 * @author jonasl
 *
 */
public interface ConnectedComponents<E> {

	public Collection<Collection<Node<E>>> computeComponents(DirectedGraph<E> dg);
}
