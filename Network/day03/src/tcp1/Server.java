package tcp1;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

class T1 extends Thread {
	String comm;
	Server server;

	T1() {
	}

	T1(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		while (true) {
			try {
				(this.server).startServer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

public class Server {

	Boolean flag = true;
	int port;
	ServerSocket serverSocket;
	Socket socket;
	OutputStream out;
	OutputStreamWriter outw;

	public Server() throws IOException {
		port = 7777;
		serverSocket = new ServerSocket(port);

		// TODO Auto-generated catch block

		// �ٸ� ���/��ǻ����(��) ����ϰ� ���� �� ��Ʈ�� ����� �� ����.
	}

	public void startServer() throws IOException {
		System.out.println("Start Server....");
		System.out.println("Ready Server....");
		while (flag) {
			try {
				socket = serverSocket.accept();
				// ���� �� ������ ��ٸ���.
				// Thread�� ��ٸ��� ��
				System.out.println("Accepted Client...." + socket.getInetAddress());
				out = socket.getOutputStream();
				outw = new OutputStreamWriter(out);
				outw.write("�ȳ�");

			} catch (IOException e) {
				throw e;
			} finally {
				if(outw != null) {
					outw.close();
				}
				if(socket != null)
					socket.close();
			}
			
			System.out.println("End Server....");
		}
	}

	public static void main(String[] args) {
		Server server = null;
		T1 t1 = null;
		try {
			server = new Server();
			server.startServer();
			//t1 = new T1(server);
			//t1.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
