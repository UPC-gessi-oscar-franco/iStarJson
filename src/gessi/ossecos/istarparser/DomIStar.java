package gessi.ossecos.istarparser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomIStar {

	private final DocumentBuilderFactory dbf;
	private final DocumentBuilder db;
	private final Document doc;
	private final GraphIS graphIS;
	private String name;

	public DomIStar(String fileName) throws ParserConfigurationException,
			SAXException, IOException {
		dbf = DocumentBuilderFactory.newInstance();
		db = dbf.newDocumentBuilder();
		doc = db.parse(new File(fileName));
		graphIS = new GraphIS();
	}

	public String getName() {
		return name;
	}

	public GraphIS getGraphDOM() {
		loadGraph(doc.getFirstChild(),"");

		return graphIS;
	}

	private NodeIS makeNode(Node subNode, String actor) {
		NodeIS nodeIS = new NodeIS();

		if(!actor.equals(""))
			nodeIS.setBundary(actor);	
		else
			nodeIS.setBundary("boundary");	
			
		
		if (subNode.getNodeName().equals("actor")
				|| subNode.getNodeName().equals("ielement")) {

			nodeIS.setId(subNode.getAttributes().getNamedItem("id")
					.getNodeValue());
			nodeIS.setName(subNode.getAttributes().getNamedItem("name")
					.getNodeValue());
			
			
		
			if (subNode.getAttributes().getNamedItem("type") != null) {
				nodeIS.setiStarType(subNode.getAttributes()
						.getNamedItem("type").getNodeValue());
				
			} else {
				nodeIS.setiStarType("actor");
				
			}

			if (subNode.getNodeName().equals("actor")) {
				nodeIS.setType(NodeType.ACTOR);

			} else {
				nodeIS.setType(NodeType.IELEMENT);
			
			}
			return nodeIS;
		} else {
			return null;
		}
	}

	private EdgeIS makeEdge(Node subNode) {
		EdgeIS edgeIS = new EdgeIS();
		Node parentNode = subNode.getParentNode();

		switch (subNode.getNodeName()) {
		case "ielementLink":
			edgeIS.setsNode(parentNode.getAttributes().getNamedItem("id")
					.getNodeValue());
			edgeIS.setfNode(subNode.getAttributes().getNamedItem("iref")
					.getNodeValue());
			edgeIS.setLabel(subNode.getAttributes().getNamedItem("value")
					.getNodeValue());
			edgeIS.setType(subNode.getAttributes().getNamedItem("type").getNodeValue());
			break;
		case "actorLink":
			edgeIS.setsNode(parentNode.getAttributes().getNamedItem("id")
					.getNodeValue());
			edgeIS.setfNode(subNode.getAttributes().getNamedItem("aref")
					.getNodeValue());
			edgeIS.setLabel(subNode.getAttributes().getNamedItem("type")
					.getNodeValue());
			edgeIS.setType(subNode.getAttributes().getNamedItem("type").getNodeValue());
			break;
		case "depender":
			parentNode = parentNode.getParentNode();
			edgeIS.setsNode(subNode.getAttributes().getNamedItem("iref")
					.getNodeValue());
			edgeIS.setfNode(parentNode.getAttributes().getNamedItem("id")
					.getNodeValue());
			edgeIS.setType("dependency");
			break;
		case "dependee":
			parentNode = parentNode.getParentNode();
			edgeIS.setsNode(parentNode.getAttributes().getNamedItem("id")
					.getNodeValue());
			edgeIS.setfNode(subNode.getAttributes().getNamedItem("iref")
					.getNodeValue());
			edgeIS.setType("dependency");
			break;
		default:
			return null;
		}

		return edgeIS;
	}

	private void loadGraph(Node node, String actor) {

		if(node.getNodeName().equals("diagram"))
		{
			this.name=node.getAttributes().getNamedItem("name").getNodeValue();
			
		}
		if (node.hasChildNodes()) {
			if (node.getNodeName().equals("actor")) {
					actor=node.getAttributes().getNamedItem("name").getNodeValue();
				
			}
			NodeList list = node.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node subNode = list.item(i);
				if (subNode.getAttributes() != null) {
					if (subNode.getAttributes().getLength() > 0) {
						graphIS.addNode(makeNode(subNode,actor));
						graphIS.addEdge(makeEdge(subNode));
					}
				}
				loadGraph(subNode,actor);
			}
		}
	}

	/**
	 *
	 * @param node
	 * @return
	 */
	public String ToString(Node node) {
		StringBuilder result = new StringBuilder();
		if (!node.hasChildNodes()) {
			return "";
		}

		NodeList list = node.getChildNodes();

		for (int i = 0; i < list.getLength(); i++) {
			Node subnode = list.item(i);
			System.out.println(subnode.getNodeType());
			System.out.print(subnode.getNodeValue());
			System.out.print("-" + subnode.getLocalName());
			System.out.print("-" + subnode.getNamespaceURI());
			System.out.print("-" + subnode.getNodeName());
			System.out.println("-" + subnode.getPrefix());
			System.out.println("-" + subnode.getTextContent());

			result.append(ToString(subnode));

		}

		return result.toString();
	}
}
