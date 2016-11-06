package com.van.DEMO;

/** 
 * <b>className: </b>RandomMathCode.java<br/>
 * <b>classDescription: </b>产生随机码<br/>
 * <b>createTime: </b>2015年4月21日
 * @author Van
 */
public class RandomMathCode {
	
	private static String[] CHARS = null;
	
	/**
	 * 获得随机位数的字符串
	 * @param LENGTH 字符串长度
	 * @return
	 */
	public static String RANDOMSTR(int LENGTH){
		if(null == CHARS){
			StringBuilder sbd = new StringBuilder();
			//77个字符
			sbd.append("a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z")
				.append(",A,B,C,D,E,F,G,H,I,G,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z")
				.append(",~,@,#,$,%,^,&,*,(,),_,+,|,`,.")
				.append(",1,2,3,4,5,6,7,8,9,0");
			CHARS = sbd.toString().split(",");
		}
		
		String rtnVal = "";
		if(LENGTH > 0){
			LENGTH = LENGTH > 77 ? 77 : LENGTH;
			StringBuilder sbd = new StringBuilder();
			for(int start = 0; start < LENGTH; start++){
				sbd.append(String.valueOf(CHARS[(int) (Math.random() * 77)]));
			}
			rtnVal = sbd.toString();
		}
		return rtnVal;
	}
	
	
	public static void main(String[] args) {
		System.out.println(RandomMathCode.RANDOMSTR(89));

	}

}
