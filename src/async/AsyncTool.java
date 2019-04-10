package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 异步工具类 有一定风险，需要理解之后才能使用
 * 
 * @author demo
 *
 */
public class AsyncTool {

	public static AsyncTool instance = new AsyncTool();

	// 线程池，专门开放一个线程用来做异步处理，用完后不关闭
	ExecutorService executor = Executors.newFixedThreadPool(1);

	public static AsyncTool getInstance() {
		return instance;
	}

	// 设计目的，让程序阻塞一段时间后再继续执行
	public boolean timeout(int times) {
		CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(times);
				return true;
			} catch (InterruptedException e) {
				return false;
			}
		}, executor);
		return future.join();
	}

}
