package hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HiveTest2 {

	public static void main(String[] args) 
		throws Exception {
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		Connection conn = 
				DriverManager.getConnection(
						"jdbc:hive2://192.168.111.100:10000/default","root","111111");
		Statement stmt = conn.createStatement();
		
		// 2006년 월 별 지연도착 평균을 
		// 구하시오

		ResultSet rs = stmt.executeQuery("select * from carAccident LIMIT 10");
		
/*		ResultSet rs = stmt.executeQuery(
				//"SELECT * FROM airline_delay LIMIT 10");
				"SELECT Year, Month, AVG(ArrDelay), AVG(DepDelay) "
				+ "FROM airline_delay "
				+ "where delayYear=2006 "
				+ "and ArrDelay > 0 "
				+ "GROUP BY Year, Month "
				+ "ORDER BY Year, Month");*/
		
		   while(rs.next()) {
		     System.out.println(rs.getInt(1) + " " + 
		    		 	        rs.getInt(2) + " " + 
		    		 	        rs.getString(3) + " " +
		    		 	        rs.getString(4) + " " +
		    		 	        rs.getInt(5) + " " +
		    		 	        rs.getInt(6) + " " +
		    		 	        rs.getInt(7) + " " +
		    		 	        rs.getInt(8) + " " +
		    		 	        rs.getInt(9) + " " +
		    		 	        rs.getString(10)
		    		 	        );
		   }
		conn.close();
		System.out.println("Success....");
	}

}
