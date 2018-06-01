package tcp4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	boolean flag = true;
	String ip;
	int port;
	Socket socket = null;

	public Client() {

	}

	public Client(String ip, int port) throws IOException {
		this.ip = ip;
		this.port = port;
		connect();
		startServer();
	}

	public void connect() {
		while (flag) {
			try {
				socket = new Socket(ip, port);
				if(socket != null && socket.isConnected()) {					
					break;
				}
			} catch (IOException e) {
				System.out.println("Re-Try");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
				}
			}
		}
	}

	public void startServer() throws IOException {
		Scanner scr = new Scanner(System.in);
		Receiver recv = new Receiver();
		recv.start();
		while (flag) {
			//System.out.println("Input Client...");
			//System.out.println("Connected with Server...");
			String str = scr.nextLine();
			new Thread(new Sender(socket, str)).start();
			if (str.equals("q")) {
				scr.close();
				break;
			}
		}
		//System.out.println("Stop Client");
	}

	class Receiver extends Thread {
		
		String msg;
		InputStream in;
		DataInputStream din;

		Receiver() throws IOException {
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
		
		
		Sender(){}
		Sender(Socket socket, String msg) throws IOException{
			this.socket = socket;
			this.msg = msg;
			out = socket.getOutputStream();
			dout = new DataOutputStream(out);
		}
		
		@Override
		public void run() {
			try {
				System.out.println("½ÇÇà");
				dout.writeUTF(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}

	public static void main(String[] args) {
		Client client;
		try {
			client = new Client("70.12.114.144", 8888);
			client.startServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
