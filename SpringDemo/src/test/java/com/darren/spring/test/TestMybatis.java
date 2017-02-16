package com.darren.spring.test;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.darren.spring.domain.Pagination;
import com.darren.spring.entity.Person;
import com.darren.spring.service.IPersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml"})
public class TestMybatis {
	
	private static Logger logger=Logger.getLogger(TestMybatis.class);
	@Resource(name="personService")
	private IPersonService personService;
	@Test
	public void testAdd(){
		Person person=new Person();
		person.setAddress("测试地址5");
		person.setGender("男5");
		person.setTel("13456789762");
		person.setUsername("小赵5");
		
		boolean result=personService.add(person);
		System.out.println(result);
	}
	@Test
	public void testPagination(){
		Pagination pagination=new Pagination();
		pagination.setStartRow(3);
		pagination.setLimitRow(2);
		List<Person> personList=personService.findByPage(pagination);
		for(Person person:personList){
			System.out.println("person:"+person.getUsername());
		}
		
	}
	
}
