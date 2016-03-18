package com.atguigu.mybatis.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.mybatis.entities.Cat;

public interface CatMapper {
	
	public List<Cat> getCatByConditions(Cat cat);
	public List<Cat> getCatByConditionsByValue(String str);
	public List<Cat> getCatByMap(Map<String,Object> map);
}
