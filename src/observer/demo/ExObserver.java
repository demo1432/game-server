package observer.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import observer.IObserver;

public class ExObserver implements IObserver {

	@Override
	public void onNotify(Object sender, int code, Object data) {
		Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

		String str = (String) data;
		logger.info("ExObserver onNotify  data = " + str);
	}

}
