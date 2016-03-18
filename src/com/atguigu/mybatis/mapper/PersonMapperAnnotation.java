package com.atguigu.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.atguigu.mybatis.entities.Person;
/**
 * 利用注解的方式进行CURD操作,注解完成需要在总局配置文件中进行配置
 * 一般不用，这样会导致sql与java代码的耦合
 * @author administrato
 *
 */
public interface PersonMapperAnnotation {
	@Insert("insert into tbl_person(name,age,birth,registerTime,salary) values(#{name},#{age},#{birth},#{registerTime},#{salary})")
	public void add(Person person);
	
	@Delete(value="delete from tbl_person where id=#{id}")
	public void delete(Integer id);
	
	@Update(value="update tbl_person set name=#{name},age=#{age},birth=#{birth},registerTime=#{registerTime},salary=#{salary} where id=#{id}")
	public void update(Person person);
	
	@Select(value="select * from tbl_person where id=#{id}")
	public Person getPerson(Integer id);
	
	@Select(value="select * from tbl_person")
	public List<Person> getAll();
}
