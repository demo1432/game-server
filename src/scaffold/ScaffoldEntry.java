package scaffold;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;

import async.demo.AsyncScaffold3;
import lambda.demo.LambdaScaffold;
import observer.demo.ObserverScaffold;
import quartz.demo.QuartzScaffold;

/**
 * 脚手架存在的基本目的是测试函数功能 在这个基础上，我希望让每次测试本身都存在意义而不是过于随意，这点可以用让测试结果持久化的方式实现
 * 测试结果要做到自身可以独立存在，而不是要通过结合源码才能理解 测试结果包括测试目的，日期，内容，效率
 * 
 * @author demo
 *
 */
public class ScaffoldEntry {

	public static void main(String[] args) {
		// 更新记录级别
		LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		Configuration conf = ctx.getConfiguration();
		conf.getLoggerConfig(LogManager.ROOT_LOGGER_NAME).setLevel(Level.INFO);
		ctx.updateLoggers(conf);

		Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
		logger.info("start");

		ObserverScaffold os = new ObserverScaffold();
		// os.done();

		AsyncScaffold3 as = new AsyncScaffold3();
		// as.done();

		QuartzScaffold qs = new QuartzScaffold();
		// qs.done();

		LambdaScaffold ls = new LambdaScaffold();
		ls.done();

		logger.info("end");
	}

}
