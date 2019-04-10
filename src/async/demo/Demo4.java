package async.demo;

import java.util.concurrent.CountDownLatch;

public class Demo4 extends BaseDemo {

	private final CountDownLatch countDownLatch = new CountDownLatch(1);

	@Override
	public void callback(long response) {

		System.out.println("得到结果");
		System.out.println(response);
		System.out.println("调用结束");

		countDownLatch.countDown();

	}

	public static void main(String[] args) {

		Demo4 demo4 = new Demo4();

		System.out.println("before call");
		
		demo4.call();

		System.out.println("after call");
		
		try {
			demo4.countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("主线程内容");

	}
}