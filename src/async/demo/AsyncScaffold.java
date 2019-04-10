package async.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import async.AsyncTool;

public class AsyncScaffold {

	// 构建异步调用
	public void done() {
		Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

		logger.info("async start");

		AsyncTool at = AsyncTool.getInstance();
		if (at.timeout(1000)) {
			logger.info("AsyncTool work ok1");
		}

		at.timeout(1000);

		logger.info("AsyncTool work ok2");

		at.timeout(1000);

		logger.info("async end");
	}

}
