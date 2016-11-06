package com.van.DEMO;

import com.van.products.entity.bean.EnumConst;

public class MathDemo {

	public static void main(String[] args) {
		System.out.println(Math.pow(2, 6));
		System.out.println(Math.PI);
		System.out.println(Math.acos(1));
		Class<EnumConst> clazz = EnumConst.class;
		
		//System.out.println(clazz.getName());
		//System.out.println(clazz.getSimpleName());
		
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		System.out.println(loader.getClass().getName());
		System.out.println(loader.getClass().getSimpleName());
		System.out.println(loader.getParent().getParent());
		System.out.println(loader.getSystemClassLoader());
		System.out.println();
	}

}
