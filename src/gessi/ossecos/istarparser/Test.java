package gessi.ossecos.istarparser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class Test {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		DomIStar domIS = new DomIStar("XwikiSD.istarml");
		GraphIS graph = domIS.getGraphDOM();

		 for(NodeIS node: graph.getNodeISList())
		 {
		 System.out.println(node.getType());
		 System.out.println(node.getiStarType());
		 System.out.println(node.getId());
		 System.out.println(node.getName());
		 
		
		 }
		for (EdgeIS edge : graph.getEdgeISList()) {
			System.out.println("in " + edge.getsNode());
			System.out.println("fi " + edge.getfNode());
			System.out.println("lab " + edge.getLabel());
		
			
		}
	}
}
