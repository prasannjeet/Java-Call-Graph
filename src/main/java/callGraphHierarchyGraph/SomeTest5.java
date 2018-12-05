package callGraphHierarchyGraph;

import ps222vt.DirectedGraph;
import ps222vt.GML;
import ps222vt.MyGML;
import ps222vt.MyGraph;
import ps222vt.Node;

import java.util.Iterator;

public class SomeTest5 {
    public static void main (String[] args) {
        DirectedGraph<Integer> dg = new MyGraph<>();
        dg.addEdgeFor(1, 2);
        dg.addEdgeFor(2, 1);
        dg.addEdgeFor(2, 3);
        dg.addEdgeFor(3,4);
        dg.addEdgeFor(4, 5);
        dg.addEdgeFor(4,3);

        //---------- if A->B then remove B->A
        Iterator<Node<Integer>> allNodes = dg.iterator();
        while (allNodes.hasNext()) {
            Node<Integer> currentNode = allNodes.next();
            Iterator<Node<Integer>> allTheSuccessors = currentNode.succsOfUnsorted();
            while (allTheSuccessors.hasNext()) {
                Node<Integer> theSuccessor = allTheSuccessors.next();
                dg.removeEdgeFor(theSuccessor.item(), currentNode.item());
            }
        }
        //----------


        GML<Integer> theGML = new MyGML<>(dg);
        System.out.println(theGML.toGML());
    }
}
