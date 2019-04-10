package async.demo;

import java.util.concurrent.CountDownLatch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AsyncScaffold4 {

	Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

	// 构建异步调用
	public void done() {

		// 记录点
		logger.info("async start");

		// 函数
		this.call();
		
		Demo4 demo = new Demo4();
		

		// 记录点
		logger.info("async end");
	}

	private CountDownLatch cdl = new CountDownLatch(1);

	public void call() {
		logger.info("async call");

		cdl.countDown();
	}
	
}
