package async.demo;

import org.apache.logging.log4j.Logger;

import async.AsyncTool;
import scaffold.LogTool;

public class AsyncScaffold {

	public void done() {
		Logger logger = LogTool.getInstance().getLogger();

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
