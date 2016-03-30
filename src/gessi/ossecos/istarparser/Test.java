package gessi.ossecos.istarparser;

import java.io.IOException;



import org.xml.sax.SAXException;


public class Test {

	public static void main(String[] args) {

//		DomIStar domIS = new DomIStar("Examples/Test2.istarml");
//		GraphIS graph = domIS.getGraphDOM();
//
//		 for(NodeIS node: graph.getNodeISList())
//		 {
//		 System.out.println(node.getType());
//		 System.out.println(node.getiStarType());
//		 System.out.println(node.getId());
//		 System.out.println(node.getName());
//		 
//		
//		 }
//		for (EdgeIS edge : graph.getEdgeISList()) {
//			System.out.println("in " + edge.getsNode());
//			System.out.println("fi " + edge.getfNode());
//			System.out.println("lab " + edge.getLabel());
//		
//			
//		}
		try {
			System.out.println(IstarToJsonConverter.converter("Examples/SECO2.istarml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
