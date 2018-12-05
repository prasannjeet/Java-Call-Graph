package ps222vt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Random;

/**
 * This class extends the GML class given in the question. All the syntax and
 * format used in generating this GML comply with yED version 3.17.1. The link
 * to the software is:
 * http://www.yworks.com/products/yfiles?utm_source=yed-3.17.1. This may also
 * work in other softwares or other versions of yED, however, that has not been
 * tested before the assignment submission.
 * 
 * Example: A simple input like this will give the following output:
 * Input:       B
 * 			   / \
 * 			  /   \
 * 			 A	   C
 *  
 *  ==> Assume a DIRECTED graph, B-->A and B-->C
 *  
 *  OUTPUT:
********************************************************************************
graph [
    comment "This is a simple graph"
    directed 1
    id 11
    label "GML By Prasannjeet Singh"
    node [
        id 0
        label "A"
        graphics [ type "roundrectangle" fill "#D258C2" ]
    ]
    node [
        id 1
        label "B"
        graphics [ type "roundrectangle" fill "#6999AC" ]
    ]
    node [
        id 2
        label "C"
        graphics [ type "roundrectangle" fill "#71D4F8" ]
    ]
    edge [
        source 1
        target 0
        graphics [width 1 type "line" fill "#0000FF" arrow "last"]
    ]
    edge [
        source 1
        target 2
        graphics [width 1 type "line" fill "#0000FF" arrow "last"]
    ]
]
********************************************************************************
 * The GML can basically be divided into 3 parts: 
 * 		1.	The Introduction, where we start the graph tag, give the title and
 * 			some label.
 * 		2. 	The Node Declaration, were we declare the nodes and their color.
 * 		3.	The Edge Declaration, where we define which node is connected
 * 			to which edge.
 * 
 * @author Prasannjeet Singh
 *
 * @param <E>
 *            A generic item.
 */
public class MyGML<E> extends GML<E> {
	/**
	 * Using a string "endl" because "\n" does not separate a line in notepad.
	 */
	String endl = System.lineSeparator();
	/**
	 * Using four spaces instead of a tab.
	 */
	String tab = "    ";

	public MyGML(DirectedGraph<E> dg) {
		super(dg);
	}

	/*
	 * => The generation of all the three parts as mentioned above are denoted by
	 * in-line comments in the method below. 
	 * 
	 * => To make sure each node is denoted by
	 * a different color, another help class ConvertColor is used in this project.
	 * Since the GML syntax needs color to be in hex format (#FFFFFF, #FA38E0, etc),
	 * we create RGB colors randomly and send this color to the function toHex in
	 * ConvertColor class, which in turn returns the HEX version of the color, which
	 * is used below.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see ps222vt.GML#toGML()
	 */
	@Override
	public String toGML() {
		Random rand = new Random();
		//Builds the first part
		StringBuilder gmlSB = new StringBuilder("graph [" + endl + tab + "comment \"This is a simple graph\"" + endl
				+ tab + "directed 1" + endl + tab + "id 11" + endl + tab + "label \"GML By Prasannjeet Singh\"" + endl);
		int nodeCount = 0;
		Iterator<Node<E>> nodeIterator = graph.iterator();
		//Builds the second part
		while (nodeIterator.hasNext()) {
            String element = nodeIterator.next().toString();
			gmlSB.append(tab + "node [" + endl + tab + tab + "id " + (nodeCount++) + endl + tab + tab + "label \""
					+ element + "\"" + endl + tab + tab
					+ "graphics [ w "+(element.length()*6+20)+".0 type \"roundrectangle\" fill \""
					+ ConvertColor.toHex(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)) + "\" ]" + endl + tab
					+ "]" + endl);
		}

		Map<Integer, ArrayList<Integer>> theMatrix = edgeMatrix(graph);
		Set<Integer> intSet = theMatrix.keySet();
		//Builds the third part
		for (int x : intSet) {
			ArrayList<Integer> tempList = theMatrix.get(x);
			for (int y : tempList) {
				gmlSB.append(tab + "edge [" + endl + tab + tab + "source " + x + endl + tab + tab + "target " + y + endl
						+ tab + tab + "graphics [width 1 type \"line\" fill \"#0000FF\" arrow \"last\"]" + endl + tab
						+ "]" + endl);
			}
		}
		gmlSB.append("]");
		return gmlSB.toString();
	}

	public String toGML (HashMap<String, HashMap<String,Integer>> edgeCount){
		Random rand = new Random();
		//Builds the first part
		StringBuilder gmlSB = new StringBuilder("graph [" + endl + tab + "comment \"This is a simple graph\"" + endl
				+ tab + "directed 1" + endl + tab + "id 11" + endl + tab + "label \"GML By Prasannjeet Singh\"" + endl);
		int nodeCount = 0;
		Iterator<Node<E>> nodeIterator = graph.iterator();
		//Builds the second part
		while (nodeIterator.hasNext()) {
			String element = nodeIterator.next().toString();
			gmlSB.append(tab + "node [" + endl + tab + tab + "id " + (nodeCount++) + endl + tab + tab + "label \""
					+ element + "\"" + endl + tab + tab
					+ "graphics [ w "+(element.length()*6+20)+".0 type \"roundrectangle\" fill \""
					+ ConvertColor.toHex(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)) + "\" ]" + endl + tab
					+ "]" + endl);
		}

		Map<Integer, ArrayList<Integer>> theMatrix = edgeMatrix(graph);
		Set<Integer> intSet = theMatrix.keySet();
		//Builds the third part
		edgeLabels(edgeCount, gmlSB, theMatrix, intSet);
		gmlSB.append("]");
		return gmlSB.toString();
	}

	public String toGMLUndirected (HashMap<String, HashMap<String,Integer>> edgeCount){
		Random rand = new Random();
		//Builds the first part
		StringBuilder gmlSB = new StringBuilder("graph [" + endl + tab + "comment \"This is a simple graph\"" + endl
				/*+ tab + "directed 0" + endl */+ tab + "id 11" + endl + tab + "label \"GML By Prasannjeet Singh\"" + endl);
		int nodeCount = 0;
		Iterator<Node<E>> nodeIterator = graph.iterator();
		//Builds the second part
		while (nodeIterator.hasNext()) {
			String element = nodeIterator.next().toString();
			gmlSB.append(tab + "node [" + endl + tab + tab + "id " + (nodeCount++) + endl + tab + tab + "label \""
					+ element + "\"" + endl + tab + tab
					+ "graphics [ w "+(element.length()*6+20)+".0 type \"roundrectangle\" fill \""
					+ ConvertColor.toHex(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)) + "\" ]" + endl + tab
					+ "]" + endl);
		}

		Map<Integer, ArrayList<Integer>> theMatrix = edgeMatrix(graph);
		Set<Integer> intSet = theMatrix.keySet();
		//Builds the third part
		edgeLabels(edgeCount, gmlSB, theMatrix, intSet);
		gmlSB.append("]");
		return gmlSB.toString();
	}

	private void edgeLabels(HashMap<String, HashMap<String, Integer>> edgeCount, StringBuilder gmlSB, Map<Integer, ArrayList<Integer>> theMatrix, Set<Integer> intSet) {
		for (int x : intSet) {
			ArrayList<Integer> tempList = theMatrix.get(x);
			for (int y : tempList) {
				int edgeLabel = edgeCount.get(convertIntToItem(x, graph)).get(convertIntToItem(y, graph));
				gmlSB.append(tab + "edge [" + endl + tab + tab + "source " + x + endl + tab + tab + "target " + y + endl
						+ tab + tab + "label \""+edgeLabel+"\"" + endl
						+ tab + tab + "graphics [width 1 type \"line\" fill \"#0000FF\" arrow \"last\"]" + endl + tab
						+ "]" + endl);
			}
		}
	}

	/**
	 * @param dg
	 *            A directed Graph
	 * @return Returns a Map with item as key and list of items it is connected to
	 *         as values.
	 */
	private Map<E, ArrayList<E>> edgeMap(DirectedGraph<E> dg) {
		Iterator<Node<E>> nodeIterator = dg.iterator();
		Map<E, ArrayList<E>> returnItem = new HashMap<E, ArrayList<E>>();
		while (nodeIterator.hasNext()) {
			ArrayList<E> edges = new ArrayList<E>();
			Node<E> tempEdgeNode = nodeIterator.next();
			Iterator<Node<E>> edgeIterator = tempEdgeNode.succsOf();
			while (edgeIterator.hasNext()) {
				edges.add(edgeIterator.next().item());
			}
			returnItem.put(tempEdgeNode.item(), edges);
		}
		return returnItem;
	}

	/**
	 * Since in the GML syntax, each node has to have an integer ID, like 1,2,3,
	 * etc. We use integer as a mapping for each item.	An inefficient way, currently too lazy to change it
	 * 
	 * @param e
	 *            A generic item
	 * @param dg
	 *            The directed graph where item e is a part of
	 * @return An integer that we assume as the ID of that particular item.
	 */
	private int convertItemToInt(E e, DirectedGraph<E> dg) {
		Iterator<Node<E>> nodeIterator = dg.iterator();
		Map<E, Integer> veryTempMap = new HashMap<E, Integer>();
		int count = 0;
		while (nodeIterator.hasNext()) {
			veryTempMap.put(nodeIterator.next().item(), count++);
		}
		return (veryTempMap.get(e));
	}

	//An inefficient way, currently too lazy to change it
	private E convertIntToItem(int a, DirectedGraph<E> dg) {
		Iterator<Node<E>> nodeIterator = dg.iterator();
		Map<E, Integer> veryTempMap = new HashMap<E, Integer>();
		int count = 0;
		while (nodeIterator.hasNext()) {
			veryTempMap.put(nodeIterator.next().item(), count++);
		}
		Set<E> keyset = veryTempMap.keySet();
		for (E theKey : keyset) {
			if (veryTempMap.get(theKey) == a){
				return theKey;
			}
		}
		return null;
	}

	/**
	 * Irrespective of the generic value our directed graph uses, the edge matrix
	 * will always comprise of integers, as by using the previous method
	 * "convertItemToInt", we made a reference to each node in the form of integer.
	 * Therefore, in this case, the key will be the nodes in form of integers and
	 * the values will be a list of integers (each integer is referenced to one
	 * node) that are connected to the key. This edge matrix is used to create the
	 * Part 3 (The Edge Declaration) as mentioned at the top.
	 * 
	 * @param dg
	 *            A directed Graph
	 * @return The edge matrix
	 */
	Map<Integer, ArrayList<Integer>> edgeMatrix(DirectedGraph<E> dg) {
		Map<E, ArrayList<E>> veryTempMap = edgeMap(dg);
		Map<Integer, ArrayList<Integer>> returnMap = new HashMap<Integer, ArrayList<Integer>>();
		Set<E> newTempSet = veryTempMap.keySet();
		for (E tempE : newTempSet) {
			ArrayList<Integer> mainList = new ArrayList<Integer>();
			ArrayList<E> tempList = veryTempMap.get(tempE);
			for (E otherE : tempList) {
				mainList.add(convertItemToInt(otherE, dg));
			}
			returnMap.put(convertItemToInt(tempE, dg), mainList);
		}
		return returnMap;
	}
}
