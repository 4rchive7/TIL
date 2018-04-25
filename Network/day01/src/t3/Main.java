package t3;

import java.util.Scanner;

class Receiver implements Runnable{

	String comm = "";
	
	public void setComm(String comm) {
		this.comm = comm;
	}
	
	@Override
	public void run() {

		System.out.println("Thread Running");
		while(true) {
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(comm != null && comm.equals("s")) {
				for(int i=0;i<=30;i++) {
					if(comm != null && comm.equals("e"))
						break;
					System.out.println(i);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			if(comm != null && comm.equals("e"))
				break;
			
		}
		
	}
	
}



public class Main {

	public static void main(String[] args) {
		Receiver r = new Receiver();
		Thread t =new Thread(r);
		t.start();
		Scanner scr = new Scanner(System.in);
		String comm = scr.nextLine();
		r.setComm(comm.trim());
		System.out.println("Main Thread");
		
		String comm2 = scr.nextLine();
		r.setComm(comm2.trim());
		scr.close();
	}

}
