package hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HiveTest {

	public static void main(String[] args) throws Exception {
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		Connection conn = DriverManager.getConnection(
				"jdbc:hive2://192.168.111.100:10000/default", "root", "111111");
		//oracle은 1521번 hive는 10000 안보이는 프로그램 service, demon이라고 하고 oracle에서 
		//응답을 대기할 때 사용되는 프로그램이라고 생각하면 될듯
		
		Statement stmt = conn.createStatement();
		// 2006년 월 별 지연출발, 지연도착 평균을 구하시오
		/*ResultSet rs = stmt.executeQuery(
				"SELECT Year, Month, AVG(ArrDelay), AVG(DepDelay) " + 
				"FROM airline_delay " + 
				"WHERE delayYear=2006 " + 
				"AND ArrDelay > 0 " + 
				"GROUP BY Year, Month " + 
				"ORDER BY Year, Month");*/
		ResultSet rs = stmt.executeQuery(
				"SELECT * FROM airline_delay LIMIT 10");
		while (rs.next()) {
			System.out.println(rs.getInt(1)+ " "
		+ rs.getInt(2)+" "+rs.getInt(3)+ " "+ rs.getInt(4));
			
		}// hive가 실행 또는 데몬형태로 실행되어 있어야 받을 수 있음
		conn.close();
		System.out.println("Success....");

	}

}
