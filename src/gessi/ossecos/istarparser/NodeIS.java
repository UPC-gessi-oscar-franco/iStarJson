package gessi.ossecos.istarparser;

/**
 * <h2>NodeIS Java class</h2>
 * <dl>
 * <dt>Purpose: Represent a node in the graph
 * <dd>
 *
 * <dt>Description:
 * <dd>This Java class provide the basic information of a graph node.
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
public class NodeIS {

	private String id;
	private String name;
	private NodeType type;
	private String iStarType;
	private String boundary;

	/**
	 * boundary is the frontier of the node
	 * 
	 * @return an actor name of the general word "boundary"
	 */
	public String getBoundary() {
		return boundary;
	}

	/**
	 * {@link NodeIS#getBoundary()}
	 */
	public void setBundary(String bundary) {
		this.boundary = bundary;
	}

	/**
	 * iStar type of the node
	 * 
	 * @return iStartType E {"actor", "goal", "task", "resource", "softgoal",
	 *         "belief"}
	 */
	public String getiStarType() {
		return iStarType;
	}

	/**
	 * {@link NodeIS#getiStarType()()}
	 */

	public void setiStarType(String iStarType) {
		this.iStarType = iStarType;
	}

	/**
	 * Id is the unique identifier of the node
	 * 
	 * @return the Id of the node (!="")
	 */
	public String getId() {
		return id;
	}

	/**
	 * {@link NodeIS#getId()}
	 */

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * the name of the node
	 * @return the name of the node (!="")
	 */
	public String getName() {
		return name;
	}
	/**
	 * {@link NodeIS#getName()}
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * The general type of the node in iStarML
	 * @return the general type of the node E {ielement,boundary,"" for actors}
	 */
	public NodeType getType() {
		return type;
	}
	/**
	 * {@link NodeIS#setType(NodeType)}
	 */

	public void setType(NodeType type) {
		this.type = type;
	}

}
