package gessi.ossecos.istarparser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import gessi.ossecos.ccistarml.ccistarmlFile;

public class IstarToJsonConverter {

	public static String converter(String file) throws Exception {
		
		ccistarmlFile f = new ccistarmlFile();
		f.loadFile(file); // put your file name here !
		f.xmlParser();
		f.istarmlParser();
		if (f.errors() > 0) {
			f.displayErrors();
			System.out.println("Cantidad de errores: " + f.errors());
			throw new Exception(file + " is not a valid istarml file");
		}
	
		
		DomIStar dom= new DomIStar(file);
		GraphIS graph= dom.getGraphDOM();
		
		
		String JsonString="";
		String modelType="dependence";
		JSONArray oNodes = new JSONArray();
		JSONArray oEdges = new JSONArray();
		JSONObject oDiagram = new JSONObject();
		
		oDiagram.put("diagram",dom.getName());
		
		
		//Nodes
		NodeIS nIs;
		
		for(Object o: graph.getNodeISList())
		{
			nIs= (NodeIS) o;
			JSONObject objN = new JSONObject();
			objN.put("id", nIs.getId());
			objN.put("name",nIs.getName());
			objN.put("elemenType",nIs.getiStarType());
			objN.put("boundary",nIs.getBoundary());
			oNodes.add(objN);

		
		}
		oDiagram.put("nodes",oNodes);
		
		//Edges
		EdgeIS eIs;
		for(Object o: graph.getEdgeISList())
		{
			eIs= (EdgeIS) o;
			JSONObject objE = new JSONObject();
			objE.put("source", eIs.getsNode());
			objE.put("target", eIs.getfNode());
			objE.put("linktype", eIs.getType().toString());
			if(!eIs.getType().toString().equals("dependency"))
			{
				modelType="rationale";
			}
			objE.put("linksubtype", eIs.getLabel());
			oEdges.add(objE);			
			System.out.println("Source " +eIs.getsNode()+" Target "+eIs.getfNode());			
		}
	
		oDiagram.put("edges",oEdges);
		oDiagram.put("modelType",modelType);
		
		JsonString = oDiagram.toJSONString();
		return JsonString;
	}
}
