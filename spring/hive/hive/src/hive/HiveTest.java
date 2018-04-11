package hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONArray;

public class HiveTest {

	public static void main(String[] args) 
		throws Exception {
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		Connection conn = 
				DriverManager.getConnection(
						"jdbc:hive2://192.168.111.102:10000/default","root","111111");
		Statement stmt = conn.createStatement();
		
		// 2006년 월 별 지연도착 평균을 
		// 구하시오
		
		//ResultSet rs = stmt.executeQuery("select * from airline LIMIT 10");
		//ResultSet rs = stmt.executeQuery("select * from car_accident LIMIT 10");
		
		ResultSet rs = stmt.executeQuery(
				//"SELECT * FROM airline_delay LIMIT 10");
				"SELECT Year, Month, Count(*) "
				+ "FROM airline_delay "
				+ "where delayYear=2006 "
				+ "and ArrDelay > 0 "
				+ "GROUP BY Year, Month "
				);
				//+ "ORDER BY Year, Month");
		
		JSONArray ja = new JSONArray();
		// [] <- 이게 하나 만들어짐
		
		while (rs.next()) {
			JSONArray data = new JSONArray();
			data.add(rs.getInt(2)+"월");
			data.add(rs.getInt(3));
			// ["1월", 20000] <- 이렇게 하나 만들어짐   (chart1)
			ja.add(data);
			
		}
		// [ [], [], [] ]  <--이런식으로 만들어짐 
		System.out.println(ja.toJSONString());
		
		conn.close();
		System.out.println("Success....");
	}

}
