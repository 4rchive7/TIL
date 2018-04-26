package http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Http2 {
	
	public static void main(String[] args) throws Exception {
		String name = "태익";
		name = URLEncoder.encode(
				new String(name.getBytes("UTF-8"))
				);
		String surl = "http://127.0.0.1/login?id=qq&pwd=11&name="+name;		
		
		URL url = new URL(surl);

		URLConnection conn = url.openConnection();
		
		conn.setConnectTimeout(5000); //5초 동안 응답이 없으면
		
		InputStream in = conn.getInputStream();
		InputStreamReader ir = new InputStreamReader(in,"UTF-8");
		BufferedReader br = new BufferedReader(ir);
		
		String str = br.readLine();
		System.out.println(str);
		
		br.close();
		ir.close();
		in.close();
	}

}
