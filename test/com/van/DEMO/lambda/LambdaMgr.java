package com.van.DEMO.lambda;

import java.io.Serializable;
import java.util.Comparator;
import java.util.function.Function;

/** 
 * @className: LambdaMgr.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年8月22日
 * @author Van
 */

@FunctionalInterface
public interface LambdaMgr<F> {

	public F lambda(Serializable src);
	
	default public void defined(){
		//pass
	}
}
