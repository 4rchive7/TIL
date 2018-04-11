package r;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class RTest2 {	
	public static void main(String[] args) {
		RConnection rconn = null;
		try {
			rconn = new RConnection();
			System.out.println("ok!!!");
		} catch (RserveException e) {
			System.out.println("R Connection Error");
		}
		System.out.println("Connection is completed");
		try {
			rconn.setStringEncoding("utf8");
			rconn.eval("source('C:/rproject/day09/r1.R', encoding='UTF-8')");
			
			double data[] = rconn.eval("r1()").asDoubles(); 
			// [{name:"data", datas:[1,2,3,4,5,..10]}]
			
			JSONArray ja = new JSONArray();
			JSONObject jo = new JSONObject();
			jo.put("name", "data");
			JSONArray ja_sub = new JSONArray();
			for (double d : data) {
				ja_sub.add(d);
			}
			jo.put("datas", ja_sub);
			ja.add(jo);	
			
			System.out.println(ja.toJSONString());
			
			for(double index : data)
				System.out.println(index);

		}catch(Exception e) {
			System.out.println("sdf");
			e.printStackTrace();
		}
	}

}
