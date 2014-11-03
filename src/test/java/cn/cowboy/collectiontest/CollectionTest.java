package cn.cowboy.collectiontest;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CollectionTest {
	public static void main(String[] args) {
		mapTest();
	}
	
	public static void mapTest(){
		Map<String,String> map = new TreeMap<String, String>();
		map.put("a", "b");
		map.put("c", "b");
		map.put("b", "b");
		System.out.println(map.size());
		System.out.println(map);
	}
	
	public static void SetTest(){
		Set<Student> students = new LinkedHashSet<Student>();
		students.add(new Student("s1"));
		students.add(new Student("s2"));
		
		System.out.println(students.size());
		
	}
	
	public static void objectEquals(){
		Student s1 = new Student("s1");
		Student s2 = new Student("s2");
		System.out.println(s1.equals(s2));
	}
}
