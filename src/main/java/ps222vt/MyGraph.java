package ps222vt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class MyGraph<E> implements the interface DirectedGraph<E> which was
 * given in the question statement. All manipulations on the data structures
 * used are done only in this as well as the MyNode<E> class. All other files
 * are independent of the data structures used, provided the comparable
 * interface is implemented and the object methods are overridden.
 * 
 * Following is a short description of variables used in this class: 
 * 		S: 		A HashMap with item E as key and Node<E> as the value. Used 
 * 				hash map instead of a set so that it will be relatively easier 
 * 				to search and get any nodes that are stored in the HashMap. It
 * 				is the main data-set which stores all the nodes that are
 * 				currently present in the Graph.
 * 
 * 		heads:	A HashMap with list of all the nodes that do not have any
 * 				predecessors.
 * 
 * 		tails:	A HashMap with list of all the nodes that do not have any
 * 				successors.
 * 
 * Method description can be read in the DirectedGraph<E> itnerface, however,
 * a little description has been provided where necessary.
 * 
 * @author Prasannjeet Singh
 * @param <E>
 */
public class MyGraph<E> implements DirectedGraph<E>{
	
	private Map<E,Node<E>> S  = new HashMap<E,Node<E>>();
	private Map<E,Node<E>> heads  = new HashMap<E,Node<E>>();
	private Map<E,Node<E>> tails  = new HashMap<E,Node<E>>();
	
	//A default constructor generating an empty graph
	public MyGraph(){
		S = new HashMap<E,Node<E>>();
		heads = new HashMap<E,Node<E>>();
		tails = new HashMap<E,Node<E>>();
	}
	
	private void addHeads (Node<E> e) {
		heads.put(e.item(), e);
	}
	
	private void addTails (Node<E> e) {
		tails.put(e.item(), e);
	}
	
	/* (non-Javadoc)
	 * @see ps222vt.DirectedGraph#addNodeFor(java.lang.Object)
	 * Will throw an exception if a NULL input is received.
	 * Else a new MyNode object will created with item as an element and it will be added to the HashMap S created above.
	 */
	@Override
	public Node<E> addNodeFor(E item) {
		if (item == null) throw new RuntimeException ("Received a NULL input");
		else {
		Node<E> tempNode = new MyNode<E>(item);
		if (!S.containsKey(item)) {
			addHeads(tempNode);
			addTails(tempNode);
			S.put(tempNode.item(), tempNode);
		}
		return S.get(item);
		}
	}

	/* (non-Javadoc)
	 * @see ps222vt.DirectedGraph#getNodeFor(java.lang.Object)
	 * Will throw an exception if a NULL input is received or the item is not found in the HashMap.
	 * else the item will be retrieved and returned in the form of Node<E> object.
	 */
	@Override
	public Node<E> getNodeFor(E item) {
		if (item == null) throw new RuntimeException ("Received a NULL input");
		if (!S.containsKey(item)) throw new RuntimeException ("Item not found");
		else {
			return S.get(item);
		}
	}

	/* (non-Javadoc)
	 * @see ps222vt.DirectedGraph#addEdgeFor(java.lang.Object, java.lang.Object)
	 * Exception if null object sent. Added the items to HashMap "S" if not added already.
	 * Then edge will be made and boolean value will be return according to whether the edge was added already or just now.
	 */
	@Override
	public boolean addEdgeFor(E from, E to) {
		if (from == null || to == null) throw new RuntimeException ("Received a NULL input");
		Node<E> From = (MyNode<E>) addNodeFor(from);
		Node<E> To = (MyNode<E>) addNodeFor(to);
		if (From.hasSucc(To)) return false;
		else {
			From.addSucc(To);
			To.addPred(From);
			
			tails.remove(from);
			heads.remove(to);
			return true;
		}
	}

	@Override
	public boolean containsNodeFor(E item) {
		if (item == null) throw new RuntimeException ("Received a NULL input");
		return (S.containsKey(item));
	}

	@Override
	public int nodeCount() {
		Set<E> sizeSet = S.keySet();
		return sizeSet.size();
	}

	@Override
	public int headCount() {
		return heads.size();
	}

	@Override
	public int tailCount() {
		return tails.size();
	}

	@Override
	public Iterator<Node<E>> iterator() {
		Collection<Node<E>> C = S.values();
		return C.iterator();
	}

	/*
	 * Iterates over all head nodes. Sorted set of nodes are returned in the
	 * iteration to establish a standard. (non-Javadoc)
	 * 
	 * @see ps222vt.DirectedGraph#heads()
	 */
	@Override
	public Iterator<Node<E>> heads() {
		Collection<Node<E>> C = heads.values();
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
	public Iterator<Node<E>> tails() {
		Collection<Node<E>> C = tails.values();
		return C.iterator();
	}

	@Override
	public List<E> allItems() {
		List<E> L = new LinkedList<E>();
		Collection<Node<E>> C = S.values();
		for (Node<E> e : C) {
			L.add(e.item());
		}
		return L;
	}

	@Override
	public int edgeCount() {
		Collection<Node<E>> C1 = S.values();
		int count = 0;
		Iterator<Node<E>> itr = C1.iterator();
		while (itr.hasNext()) {
			count += itr.next().outDegree();
		}
		return count;
	}

	/*
	 * Implementing this was a little tricky, as just removing the node from the
	 * main list "S" wouldn't do the trick. There are other things to be done as
	 * well. All in all, following are the 4 steps undertaken to entirely remove a
	 * node from the graph:
	 * 
	 * 		1. Find all the predecessors of the target node and remove the target 
	 * 		node as a "successor" to all these predecessors.
	 *  
	 * 		2. Find all the	successors of the target node and remove the target 
	 * 		node as a "predecessor" to all these successors. 
	 * 
	 * 		3. Now remove the node from "S". 
	 * 
	 * 		4. Then remove the node from "heads" and "tails", if present.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see ps222vt.DirectedGraph#removeNodeFor(java.lang.Object)
	 */
	@Override
	public void removeNodeFor(E item) {
		if (item == null)
			throw new RuntimeException("Received a NULL input");
		if (!containsNodeFor(item))
			throw new RuntimeException("Item not found");
		/**
		 * Below the predecessor and successor iterators iterate and copy the list of
		 * successors and predecessors to a new LinkedList, because directly working
		 * with the iterator can have some chances to invoke the
		 * ConcurrentModificationException, as I might be modifying the contents of the
		 * iterator while iterating the items at the same time.
		 */
		Node<E> tempNode = S.get(item);
		Iterator<Node<E>> itr1 = tempNode.predsOf();
		List<E> newList1 = new LinkedList<E>();
		while (itr1.hasNext())
			newList1.add(itr1.next().item());
		Iterator<E> itr1_1 = newList1.iterator();
		while (itr1_1.hasNext())
			removeEdgeFor(itr1_1.next(), item);
		Iterator<Node<E>> itr2 = tempNode.succsOf();
		List<E> newList2 = new LinkedList<E>();
		while (itr2.hasNext())
			newList2.add(itr2.next().item());
		Iterator<E> itr2_2 = newList2.iterator();
		while (itr2_2.hasNext())
			removeEdgeFor(item, itr2_2.next());
		S.remove(item);
		heads.remove(item);
		tails.remove(item);
	}

	@Override
	public boolean containsEdgeFor(E from, E to) {
		if (from == null || to == null)
			throw new RuntimeException("Received a NULL input");
		if (!containsNodeFor(from) || !containsNodeFor(to))
			return false;
		return ((S.get(from).hasSucc(new MyNode<E>(to))) && (S.get(to).hasPred(new MyNode<E>(from))));
	}

	/*
	 * Implementing removeEdgeFor also requires one to be very careful. There are
	 * many changes that can take place once an edge is removed. Such as a node can
	 * suddenly become a head or a tail. We also need to make sure both successor
	 * and predecessors are removed, as removing just one can create an ambiguity in
	 * the implementation. Following steps were taken for this method:
	 * 
	 * 		1. If the edge is present, both successors and predecessors are removed
	 * 		from the appropriate nodes.
	 * 
	 * 		2. Nodes are checked if removal of edges had made them a head or a tail.
	 * 		if so, they are saved appropriately either in "heads" or "tails".
	 * 
	 * (non-Javadoc)
	 * 
	 * @see ps222vt.DirectedGraph#removeEdgeFor(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean removeEdgeFor(E from, E to) {
		if (from == null || to == null)
			throw new RuntimeException("Received a NULL input");
		if (containsEdgeFor(from, to)) {
			((MyNode<E>) S.get(from)).removeSucc(S.get(to));
			((MyNode<E>) S.get(to)).removePred(S.get(from));
			if (S.get(from).isTail())
				addTails(S.get(from));
			if (S.get(to).isHead())
				addHeads(S.get(to));
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		Set<E> tempSetString = S.keySet();
		return tempSetString.toString();
	}
}
