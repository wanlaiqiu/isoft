package com.van.products.util.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

/** 
 * @className: AsynchronousExecutorHolder.java<br/>
 * @classDescription: 线程异步执行处理 2秒超时<br/>
 * @createTime: 2015年4月23日
 * @author Van
 */

@Component
public class AsynchronousExecutorHolder {
	
	private ExecutorService executorService;
	private ExecutorService futureExecutorService;
	
	public AsynchronousExecutorHolder(){
		this(50);
	}
	
	public AsynchronousExecutorHolder(int threadCount){
		this.executorService = Executors.newFixedThreadPool(threadCount);
		this.futureExecutorService = Executors.newFixedThreadPool(threadCount);
	}
	
	public void execute(Runnable runnable) {
		final Future<?> future = this.executorService.submit(runnable);
		this.futureExecutorService.execute(() -> {
			
			try {
				future.get(2L, TimeUnit.SECONDS);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
				future.cancel(true);
			}
		});
	}
	
	public <T> void execute(Callable<T> callable, final AsynchronousResultHandler<T> asynchronousReturnHandler) {
		final Future<T> future = this.executorService.submit(callable);
		this.futureExecutorService.execute(() -> {
			
			try {
				asynchronousReturnHandler.onReturn(future.get(2L, TimeUnit.SECONDS));
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
				asynchronousReturnHandler.onException(e);
			} catch (TimeoutException e) {
				e.printStackTrace();
				future.cancel(true);
			}
		});

	}
	
	@PreDestroy
	public void close() {
		this.executorService.shutdownNow();
		this.futureExecutorService.shutdown();
	}
	
	public String toStaticString() {
		ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) this.executorService;
		StringBuilder sbd = new StringBuilder("");
		sbd.append("executorService: ")
			.append("poolSize-").append(threadPoolExecutor.getPoolSize())
			.append(" completedTaskCount-").append(threadPoolExecutor.getCompletedTaskCount())
			.append(" activeCount-").append(threadPoolExecutor.getCompletedTaskCount())
			.append(" largestPoolSize-").append(threadPoolExecutor.getLargestPoolSize());
		
		return sbd.toString();
	}
	
}
