package async.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * CountDownLatch的使用方法
 * 
 * @author demo
 *
 */
public class AsyncScaffold2 {

	// 构建异步调用
	public void done() {
		Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

		logger.info("async start");

		CountDownLatch cdl = new CountDownLatch(2);

		// 线程池，一个future占一个线程
		ExecutorService executor = Executors.newFixedThreadPool(2);

		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(3000);
				cdl.countDown();
				return "future1 return";
			} catch (InterruptedException e) {
				return "future1 error";
			}
		}, executor);

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(5000);
				cdl.countDown();
				return "future2 return";
			} catch (InterruptedException e) {
				return "future2 error";
			}
		}, executor);

		future1.join();
		logger.info("future1 end");

		future2.join();
		logger.info("future2 end");

		try {
			logger.info("CountDownLatch start");
			cdl.await();
			logger.info("CountDownLatch end");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		// 记录点
		logger.info("async end");
	}

}
