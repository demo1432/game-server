package lambda.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LambdaScaffold {

	public void done() {
		Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info(() -> {
			return 0;
		});

		Vector<Integer> vis = new Vector<>();
		vis.add(1);
		vis.add(2);
		vis.add(3);
		vis.forEach((n) -> {
			n = n + 2;
			logger.info("vis == " + n);
		});

		List<Integer> lis = Arrays.asList(11, 22, 33);
		lis.forEach((n) -> {
			n = n + 1;
			logger.info("lis == " + n);
		});

		new Thread(() -> {
			logger.info("thread run");
		}).start();
	}

}
