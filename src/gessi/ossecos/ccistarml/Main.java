/*
 * Main.java
 * Sample of using ccistarml Java Package
 *
 * it is part of the ccistarml Java Package
 * version 0.6
 * Created on July 4 of 2007, By Carlos Cares
 * Updated to v0.6.1  on September 20 of 2007 By Carlos Cares
 */
package gessi.ossecos.ccistarml;

import gessi.ossecos.istarparser.DomIStar;
import gessi.ossecos.istarparser.EdgeIS;
import gessi.ossecos.istarparser.GraphIS;
import gessi.ossecos.istarparser.IstarToJsonConverter;
import gessi.ossecos.istarparser.NodeIS;

import java.io.IOException;
import java.lang.String;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	public Main() {
	}

	public static void main(String[] args) {
		
		 try {
			System.out.println(IstarToJsonConverter.converter("Test2.istarml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		ERelementList erL;
//		ERelement erle;
//		ccistarmlFile f = new ccistarmlFile();
//		f.loadFile("XwikiSD.istarml"); // put your file name here !
//		f.xmlParser();
//		f.istarmlParser();
//		System.out.println("Cantidad de errores: " + f.errors());
//		f.displayErrors();
//
//		if (f.xmlParser()) {
//			String filename="Test2.istarml";
//			try {
//				
//				DomIStar dom= new DomIStar(filename);
//				GraphIS g= dom.getGraphDOM();
//				NodeIS nIs;
//				for(Object o: g.getNodeISList())
//				{
//					nIs= (NodeIS) o;
//					System.out.println(nIs.getiStarType()+"-"+nIs.getName());
//				}
//				EdgeIS eIs;
//				for(Object o: g.getEdgeISList())
//				{
//					eIs= (EdgeIS) o;
//					System.out.println("Source node-"+eIs.getsNode()+"-"+" label-"+eIs.getLabel()+" Target Node-"+eIs.getfNode()+" Type-"+eIs.getType());
//				}
//			
//			} catch (ParserConfigurationException | SAXException | IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//		} else {
//			System.out.println("Bad");
//		}
//
	}
}

// System.out.println("Ok");
// erL = new ERelementList(f.xmlStructure());
// Iterator it = erL.list().iterator();
// System.out.println("Intentional Elements");
// while (it.hasNext()) {
// erle = (ERelement) it.next();
// if (erle.name.equals("graphic")) {
// System.out.println("Code:" + erle.ID + " " + erle.attribute
// + " is on diagram " + erle.diagram);
// }
// }
