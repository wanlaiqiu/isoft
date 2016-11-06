package com.van.products.util.string;
/** 
 * @className: RandomString.java<br/>
 * @classDescription: 获取指定长度(最大77)的字符串<br/>
 * @createTime: 2015年7月19日
 * @author Van
 */

public enum RandomString {
	
	INSTANCE ;
	
	private static String[] CHARS = null;
	
	public final String RANDOMSTR(int LENGTH){
		
		if(LENGTH <= 0){
			return "";
		}
		if(null == CHARS){
			StringBuilder sbd = new StringBuilder();
			//77个字符
			sbd.append("a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z")
				.append(",A,B,C,D,E,F,G,H,I,G,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z")
				.append(",~,@,#,$,%,^,&,*,(,),_,+,|,`,.")
				.append(",1,2,3,4,5,6,7,8,9,0");
			CHARS = sbd.toString().split(",");
		}
		
		LENGTH = LENGTH > 77 ? 77 : LENGTH;
		StringBuilder sbd = new StringBuilder();
		for(int start = 0; start < LENGTH; start++){
			sbd.append(String.valueOf(CHARS[(int) (Math.random() * 77)]));
		}
		
		return sbd.toString();
	}
	
}
