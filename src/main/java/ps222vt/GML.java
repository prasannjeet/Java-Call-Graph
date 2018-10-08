/**
 * 
 */
package ps222vt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Abstract class that supports GML generation. GML is a mark-up language 
 * for graphs. The abstract method <tt>toGML</tt> is responsible for constructing
 * a string representation of the mark-up. This method is then used by the
 * two concrete methods <tt>dumpGML</tt> that saves the mark-up in a file.
 * 
 * @author jonasl
 *
 */
public abstract class GML<E> {
	private static int graph_count = 0;
	protected final DirectedGraph<E> graph;
	
	public GML(DirectedGraph<E> dg) {
		graph = dg;
	}

	/**
	 * Writes an GML mark-up to a file in the directory returned
	 * by <tt>System.getProperty("user.dir")</tt>.
	 */
	public void dumpGML() {
		File f = new File(System.getProperty("user.dir"));
		String name = "directed_graph_["+(++graph_count)+"].gml";
		f = new File(f,name);
		dumpGML(f);
	}
	/**
	 * Writes an GML mark-up to the given file.
	 */
	public void dumpGML(File graph_file) {
		String gml = toGML();
		try {
			FileWriter fw = new FileWriter(graph_file);
			fw.write(gml);
			fw.close();
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		System.out.println("Graph mark-up dumped in: "+graph_file);
	}
	
	/**
	 * The GML mark-up string constructor.
	 */
	public abstract String toGML();

}
