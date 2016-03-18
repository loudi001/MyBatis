package com.atguigu.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.atguigu.mybatis.entities.Cat;
import com.atguigu.mybatis.mapper.CatMapper;

public class TestCat {

	SqlSessionFactory sqlSessionFactory = null;
	
	@Before
	public void init() throws IOException{
		//加载全局的配置文件
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		//给sqlSessionFactory赋值,获取SQLSessionFactory
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
	}
	
	@Test
	public void testGetCat() {
		SqlSession sqlSession = null;
		
		try {
			sqlSession = sqlSessionFactory.openSession();
			CatMapper mapper = sqlSession.getMapper(CatMapper.class);
			Cat cat = new Cat();
			cat.setCatName("%Cat%");
			//cat.setAge(7);
			//Cat cat2 = mapper.getCatByConditions(cat);//查询单个POJO对象
			//System.out.println(cat2);
			List<Cat> list = mapper.getCatByConditions(cat);
			for (Cat cat2 : list) {
				System.out.println(cat2);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			
			if(sqlSession!=null){				
				sqlSession.close();
			}
		}
	}
	
	@Test
	public void testGetCatByConditionsByValue() {
		SqlSession sqlSession = null;
		
		try {
			sqlSession = sqlSessionFactory.openSession();
			CatMapper mapper = sqlSession.getMapper(CatMapper.class);
			
			List<Cat> list = mapper.getCatByConditionsByValue("Cat");
			for (Cat cat : list) {
				System.out.println(cat);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			
			if(sqlSession!=null){				
				sqlSession.close();
			}
		}
	}
	
	@Test
	public void testGetCatByMap() {
		SqlSession sqlSession = null;
		
		try {
			sqlSession = sqlSessionFactory.openSession();
			CatMapper mapper = sqlSession.getMapper(CatMapper.class);
			Map map = new HashMap();
			map.put("catName", "%Cat%");
			
			List<Cat> list = mapper.getCatByMap(map);
			for (Cat cat : list) {
				System.out.println(cat);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			
			if(sqlSession!=null){				
				sqlSession.close();
			}
		}
	}
	@Test
	public void test(){
		
		
	}

}
