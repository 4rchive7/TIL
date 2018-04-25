package day01;

import java.util.Scanner;

//Thread Class 생성하는 두가지 방법 Thread 1, 2
class Thread1 extends Thread{
	
	String msg;
	Boolean flag = true;
	
	public Thread1(String msg) {
		this.msg = msg;
	}
	
	@Override
	public void run() {
		int i = 0;
		while(flag) {
			System.out.println(msg + "]"+(i++));
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	
}

class Thread2 implements Runnable{
	
	String msg;	
	Boolean flag = true;
	
	public Thread2(String msg) {
		this.msg = msg;
	}
	
	@Override
	public void run() {
		int i = 0;
		while(flag) {
			System.out.println(msg + "]\t"+(i++));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	
	
}

public class T1 {	
	public static void main(String[] args) throws InterruptedException {
		//Android에서는 Handler와 AsyncTask를 사용했지만 
		//Java에는 그런것 없음ㅋ
		/*1*/Thread1 t1 = new Thread1("t1");
		/*2-1*/Thread2 r = new Thread2("t2");
		/*2*/Thread t2 = new Thread(r);
		//1번은 Class 하나에 대한 속성으로만 사용할 수 있지만
		//2번은 이미 존재하는 Thread class를 이용해 다양한 runnable을 넣어 다형성 특성을 살릴 수 있다.
		t1.start();
		t2.start();
		Thread.sleep(5000);
		t1.setFlag(false);
		r.setFlag(false);//원래는 2-1이 없었는데 Thread 클래스에는 setFlag Method가 없으므로 .setFlag를 이용하기 위해 2-1을 만들어 제어함
	}

}
