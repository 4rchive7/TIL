package t1;

import java.util.Scanner;



public class Server {
	
	boolean flag = true;
	
	public void startServer() {
		Scanner client = new Scanner(System.in);
		while(flag){
			System.out.println("Server Ready");
			String msg = client.nextLine();
			//scanner로 입력을 기다릴 떄는 while loop가 멈춘다. 이러면 쓰레드 왜 돌림?(큰 용량을 받을 때는 더 느림)
			//Receiver라는 이름의 Thread를 만들 것이다.
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
	
	
	//요청 받아 처리하고,
	//다시 전송한다.
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
			//Sener Thread를 통해
			//Client에게 전송시킨다.
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
