package t1;

import java.util.Scanner;



public class Server {
	
	boolean flag = true;
	
	public void startServer() {
		Scanner client = new Scanner(System.in);
		while(flag){
			System.out.println("Server Ready");
			String msg = client.nextLine();
			//scanner�� �Է��� ��ٸ� ���� while loop�� �����. �̷��� ������ �� ����?(ū �뷮�� ���� ���� �� ����)
			//Receiver��� �̸��� Thread�� ���� ���̴�.
			if(msg.equals("exit"))
				break;
			Receiver receiver = new Receiver(msg);
			receiver.start();
			System.out.println(msg);
			
			
		}
	}

	public static void main(String[] args) {
		System.out.println("Server Started");
		new Server().startServer();
		System.out.println("Server Finished");
	}
	
	
	//��û �޾� ó���ϰ�,
	//�ٽ� �����Ѵ�.
	class Receiver extends Thread{
		String msg;
		
		public Receiver(){
			
		}
		
		public Receiver(String msg){
			this.msg = msg;
		}
		
		public void setMsg() {
			this.msg = msg;
		}
		@Override
		public void run() {
			for(int i=0;i<20;i++) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.print("> ");
			}
			System.out.println(msg+" : Receiver Completed");
			//Sener Thread�� ����
			//Client���� ���۽�Ų��.
			Sender sender = new Sender(msg);
			sender.start();
			
		}
		
	}//end Reciever

	class Sender extends Thread{

		String msg;
		
		public Sender() {
			
		}
		
		public Sender(String msg) {
			this.msg =msg;
		}
		
		@Override
		public void run() {
			for(int i=0;i<20;i++) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.print("> ");
			}
			System.out.println(msg + " : Send Complete");
		}
		
	}
	
}//Server Class
