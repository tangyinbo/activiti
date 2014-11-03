package cn.cowboy.collectiontest;

import java.util.Random;

public class Student {
	private String name;
	private int id;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public Student(String name) {
		super();
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println(this.getClass().getName()+"---equals");
		if(obj==null){
			return false;
		}
		
		if(!(obj instanceof Student)){
			return false;
		}
		Student destStudent = (Student) obj;
		return this.getName().equals(destStudent.getName());
	}
	
	@Override
	public int hashCode() {
		
		int hash=0;
		for(int i=0;i<this.getName().length();i++){
			hash = 31*hash+this.name.charAt(i);
		}
		System.out.println(this.getClass().getName()+"---hashCode\t:"+hash);
		return hash;
		/*return new Random().nextInt();*/
	}
}
