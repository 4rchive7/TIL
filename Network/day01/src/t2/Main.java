package t2;

class T1 extends Thread {

	int result = 1;
	Boolean flag = true;

	public int getResult() {
		return this.result;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		while (flag) {
			result++;
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

public class Main {

	public static void main(String[] args) throws InterruptedException {
		T1 t1 = new T1();
		t1.start();
		int result = 0;
		while (result <= 20) {
			result = t1.getResult();
			System.out.println(t1.getResult());

			if (result == 20) {
				t1.setFlag(false);
				break;
			}

		}

	}

}
