package tcp2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

//전체적인 구조
//socket = serverSocket.accept();를 통해 연결이 되면 각 연결에 대한 Thread를 생성하여 Service를 하게 한다.
//즉 socket이라는 파이프를 만들고 outputStream을 통해 주고 받는 것이다.
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
}


public class Server {
	Boolean flag = true;
	int port;
	ServerSocket serverSocket;
	Socket socket;
	

	public Server() throws IOException {
		port = 7777;
		serverSocket = new ServerSocket(port);
		// 다른 사람/컴퓨터이(가) 사용하고 있을 때 포트를 사용할 수 없다.
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
