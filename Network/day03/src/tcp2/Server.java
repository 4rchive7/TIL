package tcp2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

//��ü���� ����
//socket = serverSocket.accept();�� ���� ������ �Ǹ� �� ���ῡ ���� Thread�� �����Ͽ� Service�� �ϰ� �Ѵ�.
//�� socket�̶�� �������� ����� outputStream�� ���� �ְ� �޴� ���̴�.
class Sender implements Runnable {	
	Socket socket;
	OutputStream out;
	OutputStreamWriter outw;
	Sender(){		
	}
	
	Sender(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		while(true) {				
			try {				
				Thread.sleep(3000);				
				outw.write("�ȳ�");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if(outw != null)
					outw.close();
				} catch (IOException e) {}
				try {
					if(out != null)
					out.close();
				} catch (IOException e) {}
				
				try {
					if(socket != null)
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}	
}


public class Server {
	Boolean flag = true;
	int port;
	ServerSocket serverSocket;
	Socket socket;
	

	public Server() throws IOException {
		port = 7777;
		serverSocket = new ServerSocket(port);
		// �ٸ� ���/��ǻ����(��) ����ϰ� ���� �� ��Ʈ�� ����� �� ����.
	}
	
	public void startServer() throws IOException {
		while(flag) {
			Socket socket = null;
			System.out.println("Ready Server.....");			
			socket = serverSocket.accept();	
			Sender sender = new Sender(socket);
			sender.run();
			System.out.println("Accepted Client...");
			
		}
	}

	public static void main(String[] args) {
		Server2 server = null;
		try {
			server = new Server2();
			server.startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
