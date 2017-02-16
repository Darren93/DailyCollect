package com.darren.spring.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.darren.spring.dao.redis.IRedisPersonDao;
import com.darren.spring.vo.PersonVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:conf/spring-servlet.xml"})
public class TestRedis extends AbstractJUnit4SpringContextTests {
	@Autowired
	private IRedisPersonDao redisPersonDao;
	
	@Test
	public void testAddUser(){
		PersonVO personVo=new PersonVO("Darren2","1","北京3",1);
		boolean result=redisPersonDao.addPerson(personVo);
		System.out.println("保存结果:"+result);
	}
	@Test
	public void testGetUser(){
		PersonVO personVo=redisPersonDao.getPersonVo("1");
		System.out.println(personVo.getName()+">>"+personVo.getAddress());
	}
	@Test
	public void testAddUserList(){
		List<PersonVO> personVoList=new ArrayList<PersonVO>();
		
		PersonVO personVo=new PersonVO("JACK","0","地址1",3);
		PersonVO personVo2=new PersonVO("TOM","0","地址2",4);
		PersonVO personVo3=new PersonVO("JERRY","0","地址3",5);
		
		personVoList.add(personVo);
		personVoList.add(personVo2);
		personVoList.add(personVo3);
		
		redisPersonDao.addPersonList(personVoList);
		
	}
	@Test
	public void testDelUser(){
		redisPersonDao.removePersonVo("1");
	}
	
}
