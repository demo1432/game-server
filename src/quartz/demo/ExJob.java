package quartz.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import scaffold.LogTool;

public class ExJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Logger logger = LogTool.getInstance().getLogger();
		logger.info("job working at " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
	}

}
