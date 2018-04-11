package polymorphism;

public class SamsungTV{
	public void initMethod() {
		System.out.println("객체 초기화 작업 처리..");
	}
	public void destoryMethod() {
		System.out.println("객체 삭제 전에 처리할 로직 처리..");
	}
	public void powerOn() {
		System.out.println("SamsungTV---전원을 켜다");
	}
}