package gessi.ossecos.graph;

import java.util.ArrayList;
import java.util.Hashtable;

import gessi.ossecos.istarparser.IstarToJsonConverter;
import gessi.ossecos.istarparser.NodeType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GraphModel {

	public static void IStarJsonToPdf(String iStarModel) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(iStarModel);
	
		JSONArray jsonArrayNodes = (JSONArray) jsonObject.get("nodes");
		JSONArray jsonArrayEdges = (JSONArray) jsonObject.get("edges");

		Hashtable<String, String> nodesHash = new Hashtable<String, String>();
		Hashtable<String, String> boundaryHash = new Hashtable<String, String>();
		//Hashtable<String, String> actorHash = new Hashtable<String, String>();
		ArrayList<String> boundaryItems = new ArrayList<String>();
		ArrayList<String> actorItems = new ArrayList<String>();
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());

		String nodeType;
		String nodeName;
		String nodeBoundary;
		for (int i = 0; i < jsonArrayNodes.size(); i++) {

			jsonObject = (JSONObject) jsonArrayNodes.get(i);
			nodeName = jsonObject.get("name").toString().replace(" ", "_")
					.replace("(", "").replace(")", "");
			nodeType = jsonObject.get("elemenType").toString();
			nodeBoundary = jsonObject.get("boundary").toString();
			// TODO: Verify type of diagram
			//if (!nodeType.equals("actor") & !nodeBoundary.equals("boundary")) {
				gv.addln(renderNode(nodeName, nodeType));

		//	}

			nodesHash.put(jsonObject.get("id").toString(), nodeName);
			boundaryHash.put(jsonObject.get("id").toString(), nodeBoundary);
			if (nodeType.equals("actor"))
			{
			   actorItems.add(nodeName);
			}
		}

		String edgeType="";
		String source = "";
		String target = "";
		int subgraphCount = 0;
		boolean hasCluster = false;
		nodeBoundary = "na";
		String idSource;
		String idTarget;
		for (int i = 0; i < jsonArrayEdges.size(); i++) {

			jsonObject = (JSONObject) jsonArrayEdges.get(i);
			edgeType = renderEdge("", jsonObject.get("linktype").toString());
			idSource=jsonObject.get("source").toString();
			idTarget=jsonObject.get("target").toString();
			source = nodesHash.get(idSource);
			target = nodesHash.get(idTarget);
		
			if (!boundaryHash.get(idSource).toString().equals("boundary")
					 &&!boundaryHash.get(idTarget).toString().equals("boundary")) {
				if (!boundaryHash.get(idSource).toString().equals(nodeBoundary)) {
					nodeBoundary = boundaryHash.get(idSource).toString();
					if (hasCluster) {
						gv.addln(gv.end_subgraph());
						hasCluster=false;
						
					} else {
						hasCluster = true;
					}
					gv.addln(gv.start_subgraph(subgraphCount));
					gv.addln(actorItems.get(subgraphCount++));
					gv.addln("style=filled;");
					gv.addln("color=lightgrey;");
					
				}
				gv.addln(source + "->" + target + edgeType);

			}
			else
			{
				
				boundaryItems.add(source + "->" + target + edgeType);
				
			}

		}
		gv.addln(gv.end_subgraph());
		for(String boundaryE : boundaryItems)
		{
			gv.addln(boundaryE);
		}
		gv.addln(gv.end_graph());
			
		
		System.out.println(gv.getDotSource());
	}

	public static void main(String[] args) {

		try {
			IStarJsonToPdf(IstarToJsonConverter
					.converter("Examples/Test2.istarml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// GraphViz gv = new GraphViz();
		// gv.addln(gv.start_graph());
		//
		// //-------------
		// String edge=renderEdge("", "dependency");
		// gv.addln("Actor_One->Task_One"+edge);
		// gv.addln("Task_One->Actor_Two"+edge);
		// //------------
		// gv.addln(renderNode("Actor_One", "actor"));
		// gv.addln(renderNode("Actor_Two", "actor"));
		// gv.addln(renderNode("Goal_One", "goal"));
		// gv.addln(renderNode("Task_One", "task"));
		// gv.addln(renderNode("Resource_One", "resource"));
		// gv.addln(renderNode("Belief_One", "belief"));
		// gv.addln(renderNode("Softgoal_One", "softgoal"));
		// //-------------
		//
		//
		//
		// gv.addln(gv.end_graph());
		// System.out.println(gv.getDotSource());
	}

	/**
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public static String renderNode(String name, String type) {
		String strNode = "";

		switch (type) {
		case "actor": {
			strNode = name + " [shape=circle,color=black];";
			break;
		}
		case "goal": {
			strNode = name
					+ " [shape=ellipse,width=.6, height=.7,color=darkorange,style=filled];";
			break;
		}
		case "task": {
			strNode = name + " [shape=hexagon,color=darkorange,style=filled];";
			break;
		}
		case "resource": {
			strNode = name + " [shape=box,color=darkorange,style=filled];";
			break;
		}
		case "belief": {
			strNode = name + " [shape=ellipse,color=darkorange,style=filled];";
			break;
		}
		case "softgoal": {
			strNode = name + " [shape=egg,color=darkorange,style=filled];";
			break;
		}

		}
		return strNode;

	}

	public static String renderEdge(String name, String type) {
		String strEdge = "";
		if (name.trim().equals("")) {
			name = "\"\"";
		}

		switch (type) {
		case "association": {
			strEdge = "[arrowhead=vee,label=" + name + "];";
			break;
		}
		case "dependency": {
			strEdge = "[arrowhead=curve,label=" + name + "];";
			break;
		}

		case "means_end": {
			strEdge = "[arrowhead=onormal,label=" + name + "];";
			break;
		}
		case "decomposition": {
			strEdge = "[arrowhead=tee,label=" + name + "];";
			break;
		}
		case "contribution": {
			strEdge = "[arrowhead=vee,label=" + name + "];";
			break;
		}

		}
		return strEdge;

	}
}
