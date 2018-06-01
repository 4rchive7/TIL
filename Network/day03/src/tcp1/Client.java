package tcp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	Boolean flag = true;
	String ip;
	int port;
	Socket socket;
	InputStream in;
	InputStreamReader inr;
	BufferedReader br;

	Client() {
	}

	Client(String ip, int port) throws Exception {
		this.ip = ip;
		this.port = port;
		connect();
		startClient();
	}

	public void connect() {
		while (flag) {
			try {
				socket = new Socket(ip, port);
				if (socket != null && socket.isConnected())
					flag = false;
			} catch (IOException e) {

				System.out.println("Re-Try");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
				}
			}
		}

	}

	public void startClient() throws UnknownHostException, IOException {

		System.out.println("Connected");
		in = socket.getInputStream();
		inr = new InputStreamReader(in);
		br = new BufferedReader(inr);
		String str = br.readLine();
		System.out.println(str);		

		br.close();
		inr.close();
		in.close();

	}

	public static void main(String[] args) {
		Client client = null;
		try {
			client = new Client("70.12.114.144", 7777);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
