package com.van.DEMO.lambda;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** 
 * @className: FileSuffix.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年9月12日
 * @author Van
 */

public class FileSuffix {

	private static List<String> lists = new ArrayList<>();
	private static FileFilter filter = f -> !f.getName().contains(".svn");
	private static int ADJUST = 1;
	
	public static void main(String[] args) {
		//new FileSuffix().init(System.getProperty("user.dir"));
		//Stream.generate(Math::random).limit(12).forEach(System.out::println);
		int a = Stream.iterate('A', s -> (char)(s + 1))
				.limit(26)
				.mapToInt(ch -> (int)ch)
				.peek(s -> {
					if(s > 70){
						System.out.print("big:");
					}else{
						System.out.print("small:");
					}
				}).limit(12).sum();
		System.out.println(a);
		
		
		class Student implements Cloneable {
			int age;
			String name;
			Student(int age, String name){
				this.age = age;
				this.name = name;
			}
			@Override
			public String toString() {
				return "Student " + name + ", age = " + age;
			}
			@Override
			protected Object clone() {
				Student stu = null;
				try {
					stu = (Student) super.clone();
					stu.age = ADJUST + 1;
					stu.name = stu.name.substring(0, 1) + (ADJUST + 1);
					ADJUST++;
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				return stu;
				
			}
		}
		
		Student stu = new Student(1, "A1");
		Stream.iterate(stu, s -> (Student)s.clone()).limit(12).peek(s -> s.age = s.age + 15).forEach(System.out::println);
		
	}
	
	public void init(String root) {
		File file = new File(root);
		File[] files = file.listFiles(filter);
		for(File f : files){
			this.listFiles(f);
		}
		
		Map<String, List<String>> map = lists.parallelStream()
				.collect(Collectors.groupingBy(str -> {
					int from = str.lastIndexOf(".");
					if(from > 0){
						return str.substring(from, str.length()).toLowerCase();
					}
					return "$#";
				}));
		
		TreeMap<String, List<String>> sortMap = new TreeMap<>((s1, s2) -> s2.compareTo(s1));
		sortMap.putAll(map);
		for(Entry<String, List<String>> entry : sortMap.entrySet()){
			System.out.println(entry.getKey() + "：" + entry.getValue().size());
		}
		
	}

	private void listFiles(File f) {
		
		if(f.isDirectory()){
			File[] files = f.listFiles(filter);
			for(File file : files){
				this.listFiles(file);
			}
		} else{
			lists.add(f.getName());
		}
		return;
	}
}
