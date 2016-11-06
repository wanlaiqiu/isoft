package com.van.products.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/** 
 * @className: LoggerAspect.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年8月30日
 * @author Van
 */


@Component
@Aspect
public class LoggerAspect {

	@Pointcut
	public void PointCut(){
		
	}
	
	@Before(value = "PointCut()")
	public void Before(JoinPoint point){
		System.out.println("before...");
	}
}
