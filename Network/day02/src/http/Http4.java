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
		// ����Ʈ ������ �����б�
		System.out.println("������");
		FileInputStream fileStream = null; // ���� ��Ʈ��
		int i = 0; // �� ����Ʈ�� ���� ����
		System.out.println("�߰�����");		
		
		URL url = new URL("http://127.0.0.1/fileupload.do");
		URLConnection conn = url.openConnection();
		System.out.println("�߰�����2");
		fileStream = new FileInputStream("c:\\data.txt");// ���� ��Ʈ�� ����

		
        byte[ ] readBuffer = new byte[ fileStream.available( ) ];
        while (fileStream.read( readBuffer ) != -1) //
        {
        }

        System.out.println( readBuffer ); 		
		
        /*PrintWriter out = new PrintWriter(readBuffer);
		out.print(result);
		out.close();
*/
		System.out.println("�߰�����3");
		fileStream.close();// ���� �ݱ�. ���⿡�� try/catch�� �ʿ��ϴ�.

		System.out.println("�� ���ư���");

	}

}
