package gessi.ossecos.istarparser;

import java.io.InputStream;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

import org.json.JSONTokener;

public class ValidatoriStarJSON {

	public String result="";

	/**
	 * 
	 * @param strJSON
	 * @return
	 */
	public String schemaValidator(String strJSON) {
	
		InputStream inputStream =
		 getClass().getResourceAsStream("Tmp/Sch.json");
		 JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
	     Schema schema = SchemaLoader.load(rawSchema);
		
		try {
			schema.validate(new JSONObject(strJSON));
			} catch (ValidationException e) {
			  //System.out.println(e.getMessage());
			  e.getCausingExceptions().stream()
			      .map(ValidationException::getMessage)
			      .forEach((k) -> concatMess(k));
			}
		
	return this.result;
	}
	
	private void concatMess(String k)
	{
		this.result+=k+"\n";
		
	}


	public static void main(String[] args) {
		ValidatoriStarJSON vJ = new ValidatoriStarJSON();
		 //Object resource=ValidatoriStarJSON.class.getResourceAsStream("Tmp/Sch.json");
		 String str="{\"diagram\":\"SECO1.ood\",\"node\":[{\"boundary\":\"boundary\",\"elemenType\":\"resource\",\"name\":\"License_Maintenance\",\"id\":\"07\"},{\"boundary\":\"boundary\",\"elemenType\":\"softgoal\",\"name\":\"Variety\",\"id\":\"_CEHusPReEeWhdrGaxMhY5Q\"},{\"boundary\":\"boundary\",\"elemenType\":\"softgoal\",\"name\":\"UserSatisfaction\",\"id\":\"_JjdOEPReEeWhdrGaxMhY5Q\"},{\"boundary\":\"boundary\",\"elemenType\":\"resource\",\"name\":\"MarketChanels\",\"id\":\"03\"},{\"boundary\":\"boundary\",\"elemenType\":\"actor\",\"name\":\"Developer\",\"id\":\"04\"},{\"boundary\":\"boundary\",\"elemenType\":\"actor\",\"name\":\"SwVendor\",\"id\":\"05\"},{\"boundary\":\"boundary\",\"elemenType\":\"actor\",\"name\":\"Customer\",\"id\":\"06\"}],\"edges\":[{\"linktype\":\"dependency\",\"source\":\"04\",\"target\":\"07\",\"linksubtype\":\"\"},{\"linktype\":\"dependency\",\"source\":\"07\",\"target\":\"06\",\"linksubtype\":\"\"},{\"linktype\":\"dependency\",\"source\":\"06\",\"target\":\"_CEHusPReEeWhdrGaxMhY5Q\",\"linksubtype\":\"\"},{\"linktype\":\"dependency\",\"source\":\"_CEHusPReEeWhdrGaxMhY5Q\",\"target\":\"04\",\"linksubtype\":\"\"},{\"linktype\":\"dependency\",\"source\":\"05\",\"target\":\"_JjdOEPReEeWhdrGaxMhY5Q\",\"linksubtype\":\"\"},{\"linktype\":\"dependency\",\"source\":\"_JjdOEPReEeWhdrGaxMhY5Q\",\"target\":\"06\",\"linksubtype\":\"\"},{\"linktype\":\"dependency\",\"source\":\"04\",\"target\":\"03\",\"linksubtype\":\"\"},{\"linktype\":\"dependency\",\"source\":\"03\",\"target\":\"05\",\"linksubtype\":\"\"}],\"modelType\":\"dependence\"}";
		System.out.println(vJ.schemaValidator(str));
		
	}
}
