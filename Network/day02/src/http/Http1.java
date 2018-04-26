package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Http1 {

	public static void main(String[] args){
	
		
		URL url = null;
		String address = "http://127.0.0.1/index.html";
		try {
			url = new URL(address);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		InputStreamReader in = null; //단어 단위로 받아들임
		BufferedReader br = null;	//주로 line단위로 읽어 들임
		String str = "";
		StringBuffer sb = new StringBuffer(); //String은 쉽게 변경이 안됨 그래서 buffer를 사용함
		//InputStream은 영어만 또는 파일을 전송할 때 한글은 InputStreamReader를 쓰자
		
		
		try {
			in = new InputStreamReader(url.openStream());
			br = new BufferedReader(in);
			while(true) {
				str = br.readLine();
				if(str == null) break;
				sb.append(str+"\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(br != null)
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(in != null)
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(sb);
	}

}
