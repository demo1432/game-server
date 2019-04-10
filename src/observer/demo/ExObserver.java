package observer.demo;

import org.apache.logging.log4j.Logger;

import observer.IObserver;
import scaffold.LogTool;

public class ExObserver implements IObserver {

	@Override
	public void onNotify(int code, Object data) {
		Logger logger = LogTool.getInstance().getLogger();

		String str = (String) data;
		logger.info("ExObserver onNotify  data = " + str);
	}

}
