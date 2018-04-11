package r;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class RTest4 {	
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
			RList list = rconn.eval("r3()").asList();
			System.out.println(list.size());
			String time [] = list.at("time").asStrings();
			double line2 [] = list.at("line2").asDoubles();
			double line3 [] = list.at("line3").asDoubles();
			double line4 [] = list.at("line4").asDoubles();
			
			JSONArray ja = new JSONArray();
			JSONObject jo = new JSONObject();
			
			JSONArray ja_time = new JSONArray();
			for(String s : time)
				ja_time.add(s);
			jo.put("time", ja_time);
			
			JSONArray ja_line2 = new JSONArray();
			for(double d : line2)
				ja_line2.add(d);
			jo.put("line2", ja_line2);
				
			JSONArray ja_line3 = new JSONArray();
			for(double d : line3)
				ja_line3.add(d);
			jo.put("line3", ja_line3);
			
			JSONArray ja_line4 = new JSONArray();
			for(double d : line4)
				ja_line4.add(d);
			jo.put("line4", ja_line4);
			
			ja.add(jo);
			
			System.out.println(ja.toJSONString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
