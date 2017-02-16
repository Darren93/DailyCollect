package com.darren.spring.service;

import java.util.List;

import com.darren.spring.domain.Pagination;
import com.darren.spring.entity.Person;

public interface IPersonService {
	boolean add (Person person);
	boolean saveOrUpdate(Person person);
	List<Person> findByPage(Pagination pagination);
}
