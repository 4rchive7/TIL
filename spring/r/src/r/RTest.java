package r;

import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class RTest {	
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
			//C:\rProject\day09
			rconn.eval("source('C:/rproject/day09/r1.R', encoding='UTF-8')");
			//double datas[] = rconn.eval("r1()").asDoubles(); // eval은 해당 프로그램에서 실행하겠다는 의미
			double data = rconn.eval("r1(1, 2)").asDouble(); 
//			for(double index : datas)
//				System.out.println(index);
			System.out.println("sdf : "+data);
		}catch(Exception e) {
			System.out.println("sdf");
			e.printStackTrace();
		}
	}

}
