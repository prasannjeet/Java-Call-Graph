/**
 * 
 */
package ps222vt;

import java.util.List;


/**
 * This class defines methods that are related to breadth-first traversals.
 * They can be applied on all graphs of type DirectedGraph having nodes
 * extending the class Node.
 * <p/>
 * The bfs methods returns a list (java.util.List) of nodes in breadth-first order. 
 * They also annotates each node (the public field 
 * Node.num) with a breadth-first number. 
 * 
 * 
 * @author jonasl
 *
 */
public interface BFS<E> {

    /**
     * Returns the nodes visited by a breadth-first search starting from
     * the given root node. Each visited node is also attached with 
     * a breadth-first number.
     */    
    public List<Node<E>> bfs(DirectedGraph<E> graph, Node<E> root);
    
    /**
     * Returns the nodes visited by a breadth first search starting from
     * an arbitrary set of nodes. All nodes are visited. Each visited node is 
     * also attached with a breadth-first number.
     */    
    public List<Node<E>> bfs(DirectedGraph<E> graph);
    
}
