package tcp4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import tcp4.Client.Receiver;

public class Server {

	boolean flag = true;
	ServerSocket serverSocket;
	int port;

	public Server() throws IOException {
		port = 7777;
		serverSocket = new ServerSocket(port);
	}

	public void startServer() throws IOException {
		Scanner scr = new Scanner(System.in);
		Socket socket = null;
		int cnt = 0;
		while (flag) {			
			System.out.println("Input Server...");	
			socket = serverSocket.accept();
			System.out.println("Connected with Client...");
			if(cnt == 0) {	
				Receiver recv = new Receiver(socket);
				recv.start();
				cnt++;
			}
			System.out.println("Escape from Receiver...");
			String str = scr.nextLine();
			System.out.println("입력한 값 : "+str);
			new Thread(new Sender(socket, str)).start();
			if (str.equals("q")) {
				scr.close();
				break;
			}
			
		}
		System.out.println("Stop Server");
	}

	class Receiver extends Thread {

		Socket socket;
		String msg;
		InputStream in;
		DataInputStream din;

		Receiver() {
		}

		Receiver(Socket socket) throws IOException {
			System.out.println("Receiver called");
			this.socket = socket;
			in = socket.getInputStream();
			din = new DataInputStream(in);			
		}

		@Override
		public void run() {
			String msg = "";
			while (true) {
				try {	
					if(socket != null)
						System.out.println(din.readUTF());
				} catch (IOException e) {
					
					
				}
			}
		}
	}

	class Sender implements Runnable {
		Socket socket;
		String msg;
		OutputStream out;
		DataOutputStream dout;

		Sender() {
		}

		Sender(Socket socket, String msg) throws IOException {
			this.socket = socket;
			this.msg = msg;
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
		}

		@Override
		public void run() {
			try {
				System.out.println("실행");
				dout.writeUTF(msg);
			} catch (IOException e) {
				
			}

		}

	}

	public static void main(String[] args) {
		Server server = null;
		try {
			server = new Server();
			server.startServer();
		} catch (IOException e) {			
		}

	}

}
