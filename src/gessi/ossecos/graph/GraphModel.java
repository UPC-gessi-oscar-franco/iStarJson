package gessi.ossecos.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import gessi.ossecos.istarparser.IstarToJsonConverter;
import gessi.ossecos.istarparser.NodeType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GraphModel {

	public static void IStarJsonToGraphFile(String iStarModel, String layout,
			String typeGraph) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(iStarModel);

		JSONArray jsonArrayNodes = (JSONArray) jsonObject.get("nodes");
		JSONArray jsonArrayEdges = (JSONArray) jsonObject.get("edges");
		String modelType = jsonObject.get("modelType").toString();

		// System.out.println(modelType);
		// System.out.println(jsonArrayNodes.toJSONString());
		// System.out.println(jsonArrayEdges.toJSONString());

		System.out.println(jsonObject.toJSONString());

		Hashtable<String, String> nodesHash = new Hashtable<String, String>();
		Hashtable<String, String> boundaryHash = new Hashtable<String, String>();
		Hashtable<String, Integer> countNodes = new Hashtable<String, Integer>();
		ArrayList<String> boundaryItems = new ArrayList<String>();
		ArrayList<String> actorItems = new ArrayList<String>();
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());

		String nodeType;
		String nodeName;
		String nodeBoundary;
		for (int i = 0; i < jsonArrayNodes.size(); i++) {

			jsonObject = (JSONObject) jsonArrayNodes.get(i);
			nodeName = jsonObject.get("name").toString().replace(" ", "_");
			// .replace("(", "").replace(")", "");
			nodeName = nodeName.replaceAll("[\\W]|`[_]", "");
			nodeType = jsonObject.get("elemenType").toString();
			nodeBoundary = jsonObject.get("boundary").toString();
			// TODO: Verify type of diagram
			// if (!nodeType.equals("actor") & !nodeBoundary.equals("boundary"))
			// {
			if (countNodes.get(nodeName) == null) {
				countNodes.put(nodeName, 0);
			} else {
				countNodes.put(nodeName, countNodes.get(nodeName) + 1);
				nodeName += "_" + countNodes.put(nodeName, 0);

			}
			gv.addln(renderNode(nodeName, nodeType));

			// }

			nodesHash.put(jsonObject.get("id").toString(), nodeName);
			boundaryHash.put(jsonObject.get("id").toString(), nodeBoundary);
			if (nodeType.equals("actor")) {
				actorItems.add(nodeName);
			}
		}

		String edgeType = "";
		String source = "";
		String target = "";
		String edgeSubType = "";
		int subgraphCount = 0;
		boolean hasCluster = false;
		nodeBoundary = "na";
		String idSource;
		String idTarget;
		for (int i = 0; i < jsonArrayEdges.size(); i++) {

			jsonObject = (JSONObject) jsonArrayEdges.get(i);
			edgeSubType = jsonObject.get("linksubtype").toString();
			edgeType = renderEdge(edgeSubType, jsonObject.get("linktype")
					.toString());
			idSource = jsonObject.get("source").toString();
			idTarget = jsonObject.get("target").toString();
			source = nodesHash.get(idSource);
			target = nodesHash.get(idTarget);

			if (!boundaryHash.get(idSource).toString().equals("boundary")
					&& !boundaryHash.get(idTarget).toString()
							.equals("boundary")) {
				if (!boundaryHash.get(idSource).toString().equals(nodeBoundary)) {
					nodeBoundary = boundaryHash.get(idSource).toString();
					if (hasCluster) {
						gv.addln(gv.end_subgraph());
						hasCluster = false;

					} else {
						hasCluster = true;
					}
					gv.addln(gv.start_subgraph(subgraphCount));
					gv.addln(actorItems.get(subgraphCount++));
					gv.addln("style=filled;");
					gv.addln("color=lightgrey;");

				}
				gv.addln(source + "->" + target + edgeType);

			} else {

				boundaryItems.add(source + "->" + target + edgeType);

			}

		}
		if (subgraphCount > 0) {
			gv.addln(gv.end_subgraph());
		}
		for (String boundaryE : boundaryItems) {
			gv.addln(boundaryE);
		}
		gv.addln(gv.end_graph());

		String type = typeGraph;
		// String type = "dot";
		// String type = "fig"; // open with xfig
		// String type = "pdf";
		// String type = "ps";
		// String type = "svg"; // open with inkscape
		// String type = "png";
		// String type = "plain";

		String repesentationType = layout;
		// String repesentationType= "neato";
		// String repesentationType= "fdp";
		// String repesentationType= "sfdp";
		// String repesentationType= "twopi";
		// String repesentationType= "circo";

		// //File out = new File("/tmp/out"+gv.getImageDpi()+"."+ type); //
		// Linux
		File out = new File("Examples/out." + type); // Windows
		gv.writeGraphToFile(
				gv.getGraph(gv.getDotSource(), type, repesentationType), out);

	}

	public static void main(String[] args) {

		try {
			//IStarJsonToGraphFile(
					//IstarToJsonConverter.converter("Examples/SECO1.istarml"),
					//"dot", "pdf");
					
					//IStarJsonToGraphFile("{"diagram":"SECO1.ood","nodes":[{"boundary":"boundary","elemenType":"resource","name":"License_Maintenance","id":"07"},{"boundary":"boundary","elemenType":"softgoal","name":"Variety","id":"_CEHusPReEeWhdrGaxMhY5Q"},{"boundary":"boundary","elemenType":"softgoal","name":"User Satisfaction","id":"_JjdOEPReEeWhdrGaxMhY5Q"},{"boundary":"boundary","name":"MarketChanels","id":"03","oselemenType":"resource"},{"boundary":"boundary","elemenType":"actor","name":"Developer","id":"04"},{"boundary":"boundary","elemenType":"actor","name":"SwVendor","id":"05"},{"boundary":"boundary","elemenType":"actor","name":"Customer","id":"06"}],"edges":[{"linktype":"dependency","source":"04","target":"07","linksubtype":""},{"linktype":"dependency","source":"07","target":"06","linksubtype":""},{"linktype":"dependency","source":"06","target":"_CEHusPReEeWhdrGaxMhY5Q","linksubtype":""},{"linktype":"dependency","source":"_CEHusPReEeWhdrGaxMhY5Q","target":"04","linksubtype":""},{"linktype":"dependency","source":"05","target":"_JjdOEPReEeWhdrGaxMhY5Q","linksubtype":""},{"linktype":"dependency","source":"_JjdOEPReEeWhdrGaxMhY5Q","target":"06","linksubtype":""},{"linktype":"dependency","source":"04","target":"03","linksubtype":""},{"linktype":"dependency","source":"03","target":"05","linksubtype":""}],"modelType":"dependence"}","dot", "pdf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		// System.out.println("RENDER EDGES---"+type+"---"+name);
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

		case "decomposition": {
			if (name.equals("and")) {
				strEdge = "[arrowhead=tee,label=" + name + "];";
			} else {
				strEdge = "[label=" + name + "];";
			}

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
