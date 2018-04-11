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
		//oracle�� 1521�� hive�� 10000 �Ⱥ��̴� ���α׷� service, demon�̶�� �ϰ� oracle���� 
		//������ ����� �� ���Ǵ� ���α׷��̶�� �����ϸ� �ɵ�
		
		Statement stmt = conn.createStatement();
		// 2006�� �� �� �������, �������� ����� ���Ͻÿ�
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
			
		}// hive�� ���� �Ǵ� �������·� ����Ǿ� �־�� ���� �� ����
		conn.close();
		System.out.println("Success....");

	}

}
