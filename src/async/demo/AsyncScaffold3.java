package async.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import async.AsyncTool;

public class AsyncScaffold3 {

	// 构建异步调用
	public void done() {
		Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

		// 记录点
		logger.info("async start");

		// 函数
		AsyncTool at = AsyncTool.getInstance();
		if (at.timeout(1000)) {
			logger.info("AsyncTool work ok");
		}

		// at.timeout(3000);

		logger.info("async end1");

		at.timeout(2000);

		// 记录点
		logger.info("async end2");
	}

}
