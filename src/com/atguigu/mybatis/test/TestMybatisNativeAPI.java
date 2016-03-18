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
/**
 * 原生API实现MyBatis的CRUD 操作
 * @author administrato
 *
 */
public class TestMybatisNativeAPI {

	// 获取sqlSessionFactory
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
			

			Person person = new Person();
			person.setName("lion2"+UUID.randomUUID().toString().substring(1, 5));
			person.setAge(23);
			person.setBirth(new Date());
			person.setSalary(12300);
			person.setRegisterTime(new Date());
			//使用SQLSession直接调用器insert方法并指定所在的Mapper类和类中的方法名，并指定所需要传入的参数
			sqlSession.insert(GlobalName.PACKAGE_NAME+"add",person);
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
			sqlSession.delete(GlobalName.PACKAGE_NAME+"delete", 4);
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
			
			Person person = new Person();
			person.setId(5);
			person.setName("z5");
			person.setAge(21);
			person.setBirth(new Date());
			person.setRegisterTime(new Date());
			person.setSalary(24);

			sqlSession.update(GlobalName.PACKAGE_NAME+"update",person);
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
			//查询一条数据时直接使用sqlSession.selectOne方法，返回结果为一个单个的值
			Person person = sqlSession.selectOne(GlobalName.PACKAGE_NAME+"getPerson",1);
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
			//查询多条记录，可以调用sqlSession.selectList，返回结果为一个集合
			List<Person> list = sqlSession.selectList(GlobalName.PACKAGE_NAME+"getAll");
			
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
