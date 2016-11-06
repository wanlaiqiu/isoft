package com.van.DEMO.lambda;
/** 
 * @className: Main.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年9月12日
 * @author Van
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("java.home:" + System.getProperty("java.home"));
		System.out.println("user.home:" + System.getProperty("user.home"));
		System.out.println("user.dir:" + System.getProperty("user.dir"));
		System.out.println("user.name:" + System.getProperty("user.name"));
		System.out.println("os.name:" + System.getProperty("os.version"));
		System.out.println("os.arch:" + System.getProperty("os.arch"));
	}
}
