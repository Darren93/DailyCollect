package com.darren.spring.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darren.spring.dao.PersonMapper;
import com.darren.spring.domain.Pagination;
import com.darren.spring.entity.Person;
import com.darren.spring.service.IPersonService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Transactional
@Service("personService")
public class PersonServiceImpl implements IPersonService {
	@Resource
	private PersonMapper personMapper;
	public boolean add(Person person) {
		// TODO Auto-generated method stub
		boolean flag;
		try {
			personMapper.insert(person);
			flag=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}
	public boolean saveOrUpdate(Person person) {
		// TODO Auto-generated method stub
		boolean flag=false;
		return false;
	}
	public List<Person> findByPage(Pagination pagination) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pagination.getStartRow(), pagination.getLimitRow());
		List<Person> personList=personMapper.selectAll();
		PageInfo<Person> list=new PageInfo<Person>(personList);
		return list.getList();
	}

}
