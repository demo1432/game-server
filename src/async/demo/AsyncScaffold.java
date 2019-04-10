package async.demo;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AsyncScaffold {

	// 构建异步调用
	public void done() {
		Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

		// 记录点
		logger.info("async start");

		// 函数
		Future<Long> future = call();
		logger.info(future);
		this.shutdown();

		// this.call();

		// 记录点
		logger.info("async end");
	}

	private Random random = new Random(System.currentTimeMillis());
	private ExecutorService tp = Executors.newSingleThreadExecutor();

	public Future<Long> call() {
		return tp.submit(() -> {
			long res = random.nextInt(10);
			Thread.sleep(1000 * res);
			return res;
		});
	}

	public void shutdown() {
		tp.shutdown();
	}

	public void call2() {

	}

}
