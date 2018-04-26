package http;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class Http3 {

	public static void main(String[] args) throws Exception{
		URL url = new URL("http://127.0.0.1/putty.exe");
		
		InputStream in = url.openStream();
		FileOutputStream out = new FileOutputStream("c:\\putty2.exe");
		
		int i = 0;
		while(true) {
			i = in.read();
			System.out.print(i);
			if(i == -1) {//i == -1은 파일의 끝을 의미함
				break;
			}
			out.write(i);
		}
		in.close();
		out.close();

	}

}
