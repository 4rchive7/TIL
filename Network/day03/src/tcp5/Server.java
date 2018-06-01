package tcp5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

public class Server {

	ServerSocket serverSocket;
	Boolean flag = true;
	ArrayList<DataOutputStream> list = new ArrayList<>();

	public Server() throws IOException {
		serverSocket = new ServerSocket(9999);
		System.out.println("Server Ready...");
	}

	// client accept
	// while loop
	public void start() throws IOException {
		while (flag) {
			Socket socket = serverSocket.accept();
			System.out.println("NewClient Connected : " + socket.getInetAddress());
			Receiver reciever = new Receiver(socket);
			reciever.start();
		}
	}

	class SendHttp extends Thread {
		String temp;
		String speed;
		String urls = "http://127.0.0.1/ws/main.do";

		public SendHttp() {
		}

		public SendHttp(String speed, String temp) {
			this.speed = speed;
			this.temp = temp;
		}

		@Override
		public void run() {
			urls = urls + "?speed=" + speed+"&"+"temp="+temp;	
			try {
				URL url = new URL(urls);				
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("POST");
				conn.setConnectTimeout(5000);
				conn.getInputStream();
				System.out.println("Http Ok");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Http Error");
			}
		}
	}

	class Receiver extends Thread {

		InputStream in;
		DataInputStream din;
		OutputStream out;
		DataOutputStream dout;

		public Receiver() {
		}

		public Receiver(Socket socket) throws IOException {
			in = socket.getInputStream();
			din = new DataInputStream(in);

			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
			list.add(dout);
			System.out.println(list.size() + " pc are connected");
		}

		@Override
		public void run() {
			while (din != null) {
				String str = null;
				;
				try {
					str = din.readUTF();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					break;
				}

				if (str != null && str.equals("q")) {
					break;
				}
				
				int partition = str.indexOf(',');
				String speed = str.substring(0, partition).trim();
				String temp = str.substring(partition+1, str.length()).trim();
				
				System.out.println(str);
				SendHttp http = new SendHttp(speed, temp);
				http.start();
			}
			list.remove(dout); // dout은 아직 이 class 안에서 존재했기 때문에 무슨 dout인지 정해줄 필요 없음
			if (dout != null) {
				try {
					dout.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {
		Server server = null;
		try {
			server = new Server();
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
