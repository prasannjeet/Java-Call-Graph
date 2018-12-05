package ps222vt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class MyNode<E> extends the abstract class Node<E> which is given in the
 * question statement. Along with that, it also implements the Comparable class,
 * as we compare two objects many times in the course of this program. In this
 * implementation of Graph, each node stores the information of it's successor
 * as well as predecessor nodes in a HashMap.
 * 
 * Not much description has been provided for each methods as those can be read
 * in the Node<E> abstract class. However, things have been explained briefly as
 * and where required.
 * 
 * Further Improvement: Changing the data structure for saving the successor and
 * predecessor nodes from a nested hash map to two separate hash sets or hash
 * maps.
 * 
 * @author Prasannjeet Singh
 * @param <E>
 */
public class MyNode<E> extends Node<E> implements Comparable<Node<E>> {
	/**
	 * The data structure used to store successors and predecessors here is a
	 * HashMap. Where the key tells whether we need to get successors or
	 * predecessors, and the values are again a HashMap with item E as key, and
	 * Node<E> as values.
	 * 
	 * This could alternatively have been done just by declaring two different
	 * HashMaps, or two different Sets, which would eventually have been a little
	 * easier to understand, but by the time I realized this, I had already built
	 * half of the data structure, so I decided to continue with this. It's due to
	 * this HashMap, I also defined two String values S and P which act as "key"
	 * values to the HashMap "M". As writing "successor" and "predecessor" was
	 * getting a bit tedious after some time, and there were chances of spelling
	 * error and later a NulledPointerException!
	 * 
	 * Other variables used are: 
	 * 		dfs_count: 		Used to attach a DFS number to each node. 
	 * 		po_count: 		Used to attach a Post Order number to each Node. 
	 * 		bfs_count:		Used to attach a Breadth First Number to each Node. 
	 * 		
	 * 		Note that only one of these is used depending upon which algorithm 
	 * 		are we currently implementing.
	 */
	Map<String, HashMap<E, Node<E>>> M = new HashMap<String, HashMap<E, Node<E>>>();
	private String S = "successor", P = "predecessor";
	public int dfs_count = 0;
	public int po_count = 0;
	public int bfs_count = 0;

	/**
	 * @param item
	 *            Called the super constructor. Also initialized the hash-map for
	 *            successor list and predecessors list.
	 */
	protected MyNode(E item) {
		super(item);
		M.put(S, new HashMap<E, Node<E>>());
		M.put(P, new HashMap<E, Node<E>>());
	}

	@Override
	public boolean hasSucc(Node<E> node) {
		return (M.get(S).containsKey(node.item()));
	}

	@Override
	public boolean hasPred(Node<E> node) {
		return (M.get(P).containsKey(node.item()));
	}

	@Override
	public int outDegree() {
		return M.get(S).size();
	}

	@Override
	public int inDegree() {
		return M.get(P).size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ps222vt.Node#succsOf() Since the successors as well as predecessors are
	 * stored in a HashMap, I first created a collection with all the values entries
	 * of the hashMap, then called the iterator on that collection. This is for both
	 * succOf() and predsOf() iterator.
	 * 
	 * Edit Update: The collection is also sorted before sending an iterator via an
	 * ArrayList. This is done to establish a standard on which node to choose next
	 * when implementing a DFS/BFS or Post Order.
	 * 
	 */
	@Override
	public Iterator<Node<E>> succsOf() {
		Collection<Node<E>> C = M.get(S).values();
		List<Node<E>> tempList = new ArrayList<Node<E>>();
		for (Node<E> newNode : C)
			tempList.add(newNode);
		Comparator<Node<E>> tempComparator = new Comparator<Node<E>>() {
			@SuppressWarnings("unchecked")
			@Override
			public int compare(Node<E> o1, Node<E> o2) {
				return ((Comparable<E>) o1.item()).compareTo(o2.item());
			}
		};
		tempList.sort(tempComparator);
		return tempList.iterator();
	}

    public Iterator<Node<E>> succsOfUnsorted() {
        return M.get(S).values().iterator();
    }


    @Override
	public Iterator<Node<E>> predsOf() {
		Collection<Node<E>> C = M.get(P).values();
		List<Node<E>> tempList = new ArrayList<Node<E>>();
		for (Node<E> newNode : C)
			tempList.add(newNode);
		Comparator<Node<E>> tempComparator = new Comparator<Node<E>>() {
			@SuppressWarnings("unchecked")
			@Override
			public int compare(Node<E> o1, Node<E> o2) {
				return ((Comparable<E>) o1.item()).compareTo(o2.item());
			}
		};
		tempList.sort(tempComparator);
		return tempList.iterator();
	}

	@Override
	public Iterator<Node<E>> predsOfUnsorted() {
		return M.get(P).values().iterator();
	}

	@Override
	protected void addSucc(Node<E> succ) {
		M.get(S).put(succ.item(), succ);
	}

	@Override
	protected void addPred(Node<E> pred) {
		M.get(P).put(pred.item(), pred);
	}

	@Override
	protected void removeSucc(Node<E> succ) {
		M.get(S).remove(succ.item());
	}

	@Override
	protected void removePred(Node<E> pred) {
		M.get(P).remove(pred.item());
	}

	@Override
	protected void disconnect() {
		M.get(P).clear();
		M.get(S).clear();
	}

	/*
	 * Implementing Comparable below. 
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(Node<E> e) {
		return ((Comparable<E>) item()).compareTo(e.item());
	}

	/*
	 * Overriding object methods below
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		boolean sameSame = false;
		if (object != null && object instanceof Node<?>) {
			sameSame = item() == ((Node<?>) object).item();
		}
		return sameSame;
	}

	@Override
	public int hashCode() {
		String tempString = toString();
		return tempString.hashCode();
	}
}
