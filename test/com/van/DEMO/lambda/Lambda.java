package com.van.DEMO.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import net.sf.ehcache.search.aggregator.Average;
import static java.util.Comparator.*;

/** 
 * @className: Lambda.java<br/>
 * @classDescription: Description here<br/>
 * @createTime: 2015年8月22日
 * @author Van
 */
public class Lambda {

	public static void main(String[] args) {
		User[] users = {new User("u3",10), new User("u1", 9), new User("u2", 20), new User("u5", 50)};
		
		List<User> lists = Arrays.asList(users);
		
		lists = lists.stream().filter(u -> u.getAge() > 12).collect(Collectors.toList());
		
		//lists = lists.stream().filter(u -> u.getAge() > 12).collect(Collectors.toList());
		//lists.forEach(System.out::println);
		
		//int total = lists.stream().map(User::getAge).reduce((sum, age) -> sum + age).get();
		//System.out.println(total);
		
		//double avg = lists.parallelStream().mapToInt(User::getAge).average().getAsDouble();
		//System.out.println(avg);
		
		IntSummaryStatistics stat = lists.stream().mapToInt(User::getAge).summaryStatistics();
		System.out.println("max:" + stat.getMax());
		System.out.println("min:" + stat.getMin());
		System.out.println("avg:" + stat.getAverage());
		System.out.println("sum:" + stat.getSum());
		System.out.println("couunt:" + stat.getCount());
		
	}
	
}

class User{
	String name;
	int age;
	
	public User(String name){
		this(name, 0);
	}
	
	public User(String name, int age){
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
	
}