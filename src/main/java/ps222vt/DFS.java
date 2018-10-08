/**
 * 
 */
package ps222vt;

import java.util.List;


/**
 * This class defines methods that are related to depth-first traversals.
 * They can be applied on all graphs of type DirectedGraph having nodes
 * extending the class Node.
 * <p/>
 * The dfs methods returns a list (java.util.List) of nodes in depth-first order. 
 * They also annotates each node (the public field 
 * Node.num) with a depth-first number. 
 * <p/>
 * The postOrder methods returns a list (java.util.List) of nodes in post-order of the 
 * depth first tree. They also annotates each node (the public field 
 * Node.num) with a post-order number. 
 * 
 * 
 * 
 * @author jonasl
 *
 */
public interface DFS<E> {

    /**
     * Returns the nodes visited by a depth first search starting from
     * the given root node. Each visited node is also attached with 
     * a depth-first number.
     */    
    public List<Node<E>> dfs(DirectedGraph<E> graph, Node<E> root);
    
    /**
     * Returns the nodes visited by a depth first search starting from
     * an arbitrary set of nodes. All nodes are visited. Each visited node is 
     * also attached with a depth-first number.
     */    
    public List<Node<E>> dfs(DirectedGraph<E> graph);
    
    /**
     * Returns a list of nodes ordered as 
     * post-order of the depth first tree resulting from a
     * depth first search starting at the given root node. 
     * Notice, it only visits nodes reachable from given 
     * root node.
     * </p>
     * The algorithm also attaches a post-order number
     * to each visited node.
     */ 
    public List<Node<E>> postOrder(DirectedGraph<E> g, Node<E> root);
    
    /**
     * Returns a list of ALL nodes in the graph ordered as 
     * post-order of the depth first forest resulting from
     * depth first search starting at arbitrary start nodes.
     * </p>
     * The algorithm also attaches a post-order number
     * to each visited node. 
     */ 
    public List<Node<E>> postOrder(DirectedGraph<E> g);
    
    /**
     * Returns a list of ALL nodes in the graph ordered as 
     * post-order of the depth first forest resulting from
     * depth first search starting at arbitrary start nodes.
     * </p>
     * The algorithm attaches a depth-first number if <tt>attach_dfs_number</tt> 
     * is <tt>true</tt>, otherwise it attaches a post-order number. 
     */ 
    public List<Node<E>> postOrder(DirectedGraph<E> g, boolean attach_dfs_number);
    
    /**
     * Returns <tt>true</tt> if the graph contains one or more cycles, 
     * otherwise <tt>false</tt>
     */
	public boolean isCyclic(DirectedGraph<E> graph);
	
	/**
     * Returns a list of all nodes in the graph ordered topological.
     * The algorithm assumes that the graph is acyclic. The result for
     * graphs with cycles are undefined. 
     */
	public List<Node<E>> topSort(DirectedGraph<E> graph);


 
}
