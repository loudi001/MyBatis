package com.atguigu.mybatis.entities;

import java.util.Date;

public class Cat {

	 private Integer id;
	 private String  catName;
	 private int     age;
	 private Date    birth;
	 
	 public Cat(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Cat [id=" + id + ", catName=" + catName + ", age=" + age
				+ ", birth=" + birth + "]";
	}
	 
	 
}
