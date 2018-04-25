package day01;

import java.util.Scanner;

//Thread Class �����ϴ� �ΰ��� ��� Thread 1, 2
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
		//Android������ Handler�� AsyncTask�� ��������� 
		//Java���� �׷��� ������
		/*1*/Thread1 t1 = new Thread1("t1");
		/*2-1*/Thread2 r = new Thread2("t2");
		/*2*/Thread t2 = new Thread(r);
		//1���� Class �ϳ��� ���� �Ӽ����θ� ����� �� ������
		//2���� �̹� �����ϴ� Thread class�� �̿��� �پ��� runnable�� �־� ������ Ư���� �츱 �� �ִ�.
		t1.start();
		t2.start();
		Thread.sleep(5000);
		t1.setFlag(false);
		r.setFlag(false);//������ 2-1�� �����µ� Thread Ŭ�������� setFlag Method�� �����Ƿ� .setFlag�� �̿��ϱ� ���� 2-1�� ����� ������
	}

}
