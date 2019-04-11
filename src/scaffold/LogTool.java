package scaffold;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;

/**
 * 
 * 日志工具类
 * 
 * @author demo
 *
 */
public class LogTool {

	private static LogTool instance = new LogTool();

	public static LogTool getInstance() {
		return instance;
	}

	// 返回Logger方便外部使用
	public Logger getLogger() {
		return LogManager.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
	}

	// 配置级别
	public void config() {
		LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		Configuration conf = ctx.getConfiguration();
		conf.getLoggerConfig(LogManager.ROOT_LOGGER_NAME).setLevel(Level.INFO);
		ctx.updateLoggers(conf);
	}

}
