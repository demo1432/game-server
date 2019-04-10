package async.demo;

import java.util.Random;

public class AsyncCall {

	private Random random = new Random(System.currentTimeMillis());

	// demo1,2,4,5调用方法
	public void call(BaseDemo demo) {

		new Thread(() -> {
			long res = random.nextInt(10);

			try {
				Thread.sleep(res * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			demo.callback(res);
		}).start();

	}

}
