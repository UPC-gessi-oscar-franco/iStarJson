package gessi.ossecos.istarparser;

/**
 * <h2>EdgeIS Java class</h2>
 * <dl>
 * <dt>Purpose: Represent an edge  in the graph
 * <dd>
 *
 * <dt>Description:
 * <dd>This Java class provide the basic information of a graph edge.
 * <dd>
 * 
 * @see gessi.ossecos.istarparser.GraphIS
 * @author Oscsr Franco-Bedoya (<a
 *         href="https://oscarfrancobedoya.wordpress.com/"
 *         >ohernan@essi.upc.edu</a>) </dd>
 *
 *         </dl>
 *
 */
public class EdgeIS {
	private String sNode;
	private String fNode;
	private String label;
	private String type;

	/**
	 * Java class constructor
	 */
	public EdgeIS() {
		label = "";
	}

	/**
	 * sNode is the source node of the edge
	 * 
	 * @return the source node of the edge (!="")
	 */
	public String getsNode() {
		return sNode;
	}

	/**
	 * {@link EdgeIS#getsNode()}
	 */
	public void setsNode(String sNode) {
		this.sNode = sNode;
	}

	/**
	 * fNode is the target node of the edge
	 * 
	 * @return the target node of the edge (!="")
	 */
	public String getfNode() {
		return fNode;
	}

	/**
	 * {@link EdgeIS#getfNode()}
	 */
	public void setfNode(String fNode) {
		this.fNode = fNode;
	}

	/**
	 * label of the edge
	 * 
	 * @return the edge label can be null of empty
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * {@link EdgeIS#getLabel()}
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * The type of the iStar node ]
	 * 
	 * @return the iStar type of the edge E {"association", "dependency",
	 *         "means_end", "decomposition", "contribution"}
	 */
	public String getType() {
		return type;
	}

	/**
	 * {@link EdgeIS#getType()}
	 */
	public void setType(String type) {
		/**
		 * TODO: validate that the type E {"association", "dependency",
		 *         "means_end", "decomposition", "contribution"}
		 */
		this.type = type;
	}

}
