package com.atguigu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.mybatis.entities.Person;
import com.atguigu.mybatis.mapper.PersonMapper;

public class TestMybatis01 {

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
			PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);

			Person person = new Person();
			person.setName("lion"+UUID.randomUUID().toString().substring(1, 4));
			person.setAge(23);
			person.setBirth(new Date());
			person.setSalary(12300);
			person.setRegisterTime(new Date());
			
			mapper.add(person);	
			System.out.println("Person's id --->"+person.getId());
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
			mapper.delete(3);
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
			person.setName("z4");
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
