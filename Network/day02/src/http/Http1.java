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
		
		
		InputStreamReader in = null; //�ܾ� ������ �޾Ƶ���
		BufferedReader br = null;	//�ַ� line������ �о� ����
		String str = "";
		StringBuffer sb = new StringBuffer(); //String�� ���� ������ �ȵ� �׷��� buffer�� �����
		//InputStream�� ��� �Ǵ� ������ ������ �� �ѱ��� InputStreamReader�� ����
		
		
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
