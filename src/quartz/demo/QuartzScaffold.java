package quartz.demo;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzScaffold {

	public void done() {
		Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

		try {
			// 创建一个调度器工厂
			SchedulerFactory factory = new StdSchedulerFactory();

			// 任务执行时间
			// Date runTime = DateBuilder.evenMinuteDate(new Date());
			Date runTime = DateBuilder.evenSecondDateAfterNow();

			// 新建JobDetail对象并绑定一个任务
			JobDetail jobDetail = JobBuilder.newJob(ExJob.class).withIdentity("demo_job", "demo_group").build();

			// 定义调度规则
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("demo_trigger", "demo_group")
					// .startNow()//立即执行
					.startAt(new Date())// 设置触发开始的时间
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1)// 时间间隔
							.withRepeatCount(5)// 重复次数(n+1),比如这里将执行6次
					).build();// 生成触发器

			// 从工厂获取一个调度器对象
			Scheduler scheduler = factory.getScheduler();
			// 绑定触发器和任务
			scheduler.scheduleJob(jobDetail, trigger);
			System.out.println(jobDetail.getKey() + " 运行在: " + runTime);
			scheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
