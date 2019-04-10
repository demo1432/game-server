package scaffold;

import org.apache.logging.log4j.Logger;

import async.demo.AsyncScaffold;
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
		LogTool.getInstance().config();
		Logger logger = LogTool.getInstance().getLogger();
		logger.info("=========================================");

		ObserverScaffold os = new ObserverScaffold();
		// os.done();

		AsyncScaffold as = new AsyncScaffold();
		as.done();

		QuartzScaffold qs = new QuartzScaffold();
		// qs.done();

		LambdaScaffold ls = new LambdaScaffold();
		// ls.done();

		logger.info("=========================================");
	}

}
