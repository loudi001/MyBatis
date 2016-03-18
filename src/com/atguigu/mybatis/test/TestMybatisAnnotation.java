package com.atguigu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.mybatis.entities.Person;
import com.atguigu.mybatis.mapper.PersonMapper;
import com.atguigu.mybatis.mapper.PersonMapperAnnotation;
/**
 * 利用注解的方式进行CRUD的操作
 * @author administrato
 *
 */
public class TestMybatisAnnotation {

	// 获取sqlSession
	SqlSessionFactory sqlSessionFactory = null;

	@Before
	public void init() throws IOException {

		InputStream inputStream = Resources
				.getResourceAsStream("mybatis-config.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testAdd() {
		SqlSession sqlSession = null;

		try {
			sqlSession = sqlSessionFactory.openSession();
			PersonMapperAnnotation mapper = sqlSession.getMapper(PersonMapperAnnotation.class);

			Person person = new Person();
			person.setName("li");
			person.setAge(23);
			person.setBirth(new Date());
			person.setSalary(12300);
			person.setRegisterTime(new Date());

			mapper.add(person);
			sqlSession.commit();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (sqlSession != null)
				sqlSession.close();
		}
	}

	@Test
	public void testDelete() {
		SqlSession sqlSession = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
			mapper.delete(5);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			sqlSession.close();
		}
	}

	@Test
	public void testUpdate() {

		SqlSession sqlSession = null;
		try {
			// 给sqlSession赋值
			sqlSession = sqlSessionFactory.openSession();
			PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);

			Person person = new Person();
			person.setId(2);
			person.setName("z6");
			person.setAge(21);
			person.setBirth(new Date());
			person.setRegisterTime(new Date());
			person.setSalary(24);

			mapper.update(person);
			;
			sqlSession.commit();
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (sqlSession != null) {

				sqlSession.close();
			}
		}
	}

	@Test
	public void testGetPerson() {

		SqlSession sqlSession = null;
		try {
			// 给sqlSession赋值
			sqlSession = sqlSessionFactory.openSession();
			PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);

			Person person = mapper.getPerson(2);
			System.out.println(person);

			sqlSession.commit();
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (sqlSession != null) {

				sqlSession.close();
			}
		}
	}
	
	@Test
	public void testGetList() {

		SqlSession sqlSession = null;
		try {
			// 给sqlSession赋值
			sqlSession = sqlSessionFactory.openSession();
			PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);

			List<Person> list = mapper.getAll();
			for (Person person : list) {
				
				System.out.println(person);
			}

			sqlSession.commit();
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (sqlSession != null) {

				sqlSession.close();
			}
		}
	}

}
