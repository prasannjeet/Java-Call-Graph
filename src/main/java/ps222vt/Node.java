/*
*   DirectedNode.java
*
*   Created 2006-feb-02, 22:28:13
*/
package ps222vt;

import java.util.*;
/**
 * An abstract <tt>Node</tt> class that serves as an interface for
 * nodes of the <tt>DirectedGraph</tt> interface. Each node is aware of 
 * (and stores) all its successor and predecessor nodes.
 * </p>
 * 
 * This class also defines a set of protected methods 
 * that can be used to add and remove adjacent nodes. 
 * They should only be used by implementors of the <tt>DirectedGraph</tt>
 * interface. Reason: improper use of these methods may put the graph in 
 * an inconsistent state. For example, by removing A as successor of B 
 * without removing B as predecessor of A.
 * </p>
 * 
 * The public integer field <tt>num</tt> is introduced to avoid externally 
 * constructed mappings between the node and some integer (e.g.
 * a dfs number). It should only be used internally and never
 * be a part of any public interface since its interpretation
 * might be changed from one algorithm to another.
 * 
 * @author jonasl
 *
 */
public abstract class Node<E> {
	/**
	 * node key used as identifier
	 */
	private E item;  
	
	/**
	 * temporary value used in algorithms.
	 */
	public int num;  
	
	/**
	 * Constructs a new node using <tt>item</tt> as key.
	 * @param item
	 */
	protected Node(E item) {
		this.item = item;
	}
	
    /**
     * Returns the item used as key for <tt>this</tt> node.
     * @return the key item
     */
	public E item() {return item;}
	
	/**
	 * Returns a string representing <tt>this</tt> node by applying
	 * <tt>toString()</tt> on the key item.
	 */
	public String toString() {return item.toString();}
	
	/**
	 * Returns <tt>true</tt> if <tt>this</tt> node has <tt>node</tt> as successor, 
	 * otherwise <tt>false</tt>.
	 * @param a possible successor node
	 * @return boolean
	 */
	public abstract boolean hasSucc(Node<E> node);
	
	/**
	 * Returns the number of successors (i.e. outgoing edges)
	 * of this node.
	 * @return node out-degree
	 */
	public abstract int outDegree();
	
	/**
	 * Returns an iterator over all successor nodes.
	 * @return successor node iterator
	 */
	public abstract Iterator<Node<E>> succsOf();

    /**
     * Returns an iterator over all successor nodes without sorting or comparing.
     * @return successor node iterator
     */
	public abstract Iterator<Node<E>> succsOfUnsorted();

	/**
	 * Returns <tt>true</tt> if <tt>this</tt> node has <tt>node</tt> as predecessor, 
	 * otherwise <tt>false</tt>.
	 * @param a possible predecessor node
	 * @return boolean
	 */
	public abstract boolean hasPred(Node<E> node);
	
	/**
	 * Returns the number of predecessors  (i.e. incoming edges)
	 * of this node.
	 * @return node out-degree
	 */
	public abstract int inDegree();
	
	/**
	 * Returns an iterator over all predecessor nodes.
	 * @return predecessor node iterator
	 */
	public abstract Iterator<Node<E>> predsOf();

	/**
	 * Returns an iterator over all predecessor nodes.
	 * @return predecessor node iterator
	 */
	public abstract Iterator<Node<E>> predsOfUnsorted();
	
	/**
	 * Returns true if <tt>this</tt> node has a reflexive edge 
	 * (i.e. an out-edge targeting itself).
	 */ 
	public boolean hasReflexiveEdges() {
		return hasSucc(this);
	}
	
	/**
	 * Removes reflexive edge if it exists.
	 * (i.e. an out-edge targeting itself).
	 */ 
	public void removeReflexiveEdges() {
		removeSucc(this);
		removePred(this);
	}
	
	/**
	 * Returns true if <tt>this</tt> node is a head (i.e. has in-degree zero).
	 * @return boolean
	 */
	public boolean isHead() {return inDegree() == 0;}
	
	/**
	 * Returns true if <tt>this</tt> node is a tail (i.e. has out-degree zero).
	 * @return boolean
	 */
	public boolean isTail() {return outDegree() == 0;}
	
	/* ====================================================================
	 * 
	 * Internal methods intended to be hidden for all clients (graph users).
	 * They should only be used by implementers of the <tt>DirectedGraph</tt>
	 * interface. Reason: improper use of these methods may put the graph in 
	 * an inconsistent state. 
	 * 
	 * ====================================================================*/	
	
	/**
	 * Adds node <tt>succ</tt> as a successor to <tt>this</tt> node.
	 */
	protected abstract void addSucc(Node<E> succ);
	/**
	 * Removes node <tt>succ</tt> as a successor to <tt>this</tt> node.
	 */
	protected abstract void removeSucc(Node<E> succ);
	/**
	 * Adds node <tt>pred</tt> as a predecessor to <tt>this</tt> node.
	 */
	protected abstract void addPred(Node<E> pred);
	/**
	 * Removes node <tt>pred</tt> as a predecessor to <tt>this</tt> node.
	 */
	protected abstract void removePred(Node<E> pred);
	/**
	 * Disconnects this node from all adjacent nodes. That is, removes all successor, 
	 * and predecessor, nodes to <tt>this</tt> node.
	 */
	protected abstract void disconnect();

		
}
