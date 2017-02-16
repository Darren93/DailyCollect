package com.darren.spring.dao.redis;

import java.util.List;

import com.darren.spring.vo.PersonVO;

public interface IRedisPersonDao{
	boolean addPerson(PersonVO personVo);
	
	void addPersonList(List<PersonVO> personVoList);
	
	boolean removePersonVo(String key);
	
	boolean removePersonVoList(List<String> keyList);
	
	boolean updatePersonVo(PersonVO personVo);
	
	PersonVO getPersonVo(String key);
}
