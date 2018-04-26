package http;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class Http4 {

	public static void main(String[] args) throws Exception {
		// 바이트 단위로 파일읽기
		System.out.println("시자흑");
		FileInputStream fileStream = null; // 파일 스트림
		int i = 0; // 한 바이트를 담을 변수
		System.out.println("중간지점");		
		
		URL url = new URL("http://127.0.0.1/fileupload.do");
		URLConnection conn = url.openConnection();
		System.out.println("중간지점2");
		fileStream = new FileInputStream("c:\\data.txt");// 파일 스트림 생성

		
        byte[ ] readBuffer = new byte[ fileStream.available( ) ];
        while (fileStream.read( readBuffer ) != -1) //
        {
        }

        System.out.println( readBuffer ); 		
		
        /*PrintWriter out = new PrintWriter(readBuffer);
		out.print(result);
		out.close();
*/
		System.out.println("중간지점3");
		fileStream.close();// 파일 닫기. 여기에도 try/catch가 필요하다.

		System.out.println("다 돌아갔다");

	}

}
