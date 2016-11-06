package com.van.products.util.thread;
/** 
 * 
 * @className: AsynchronousResultHandler.java<br/>
 * @classDescription: 线程异步执行<br/>
 * @createTime: 2015年4月23日
 * 
 * @author Van
 */
public abstract interface AsynchronousResultHandler<T> {

	public abstract void onReturn(T paramT);
	
	public abstract void onException(Exception paramException);
}
