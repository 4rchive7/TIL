package tcp2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
	Boolean flag = true;
	int port;
	ServerSocket serverSocket;


	public Server2() throws IOException {
		port = 7777;
		serverSocket = new ServerSocket(port);
		// 다른 사람/컴퓨터이(가) 사용하고 있을 때 포트를 사용할 수 없다.
	}
	
	//Accept Client Socket And
	//Sender Thread Create and Start
	static class Sender implements Runnable {
		Socket socket;
		OutputStream out;
		OutputStreamWriter outw;
		//tcp1에서 사용한 예제와 다른 점은 socket과 OutputStream 객체들을 공유하느냐 아니냐의 차이
		//만약에 공유한다면 OutputStream을 수행하는 도중에 socket에 새로운 객체가 담기게 되면
		//문제가 생길 수 있음
		
		Sender(){}
		
		Sender(Socket socket) throws IOException {
			this.socket = socket;
			out = socket.getOutputStream();
			outw = new OutputStreamWriter(out);
		}

		@Override
		public void run() {			
			try {				
				Thread.sleep(3000);				
				outw.write("안녕");
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
