package async.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AsyncScaffold2 {

	private CountDownLatch cdl = new CountDownLatch(2);

	// 构建异步调用
	public void done() {
		Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

		// 记录点
		logger.info("async start");

		// 函数
		ExecutorService executor = Executors.newFixedThreadPool(2);

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			logger.info("小红你去买瓶酒！");
			try {
				logger.info("小红出去买酒了，女孩子跑的比较慢，估计5s后才会回来...");
				Thread.sleep(5000);

				cdl.countDown();

				return "我买回来了！";
			} catch (InterruptedException e) {
				logger.info("小红路上遭遇了不测");
				return "来世再见！";
			}
		}, executor);

		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			logger.info("小明你去买包烟！");
			try {
				logger.info("小明出去买烟了，可能要3s后回来...");
				Thread.sleep(3000);

				cdl.countDown();

				return "我买回来了！";
			} catch (InterruptedException e) {
				logger.info("小明路上遭遇了不测");
				return "这是我托人带来的口信，我已经不在了。";
			}
		}, executor);

		logger.info("test 1== " + future1.join());

		logger.info("test 2== " + future2.join());
		
		//
		// logger.info("小明回来了");
		//
		// future2.join();
		//
		// logger.info("小红回来了");

		// CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
		// cyclicBarrier.reset();

		// try {
		// cdl.await();
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }

		future2.thenAccept((e) -> {
			logger.info("小红说：" + e);
		});

		future1.thenAccept((e) -> {
			logger.info("小明说：" + e);
		});

		logger.info("loading......");
		logger.info("我觉得无聊甚至去了趟厕所。");
		logger.info("loading......");

		// this.call();

		// executor.shutdown();

		// 记录点
		logger.info("async end");
	}

}
