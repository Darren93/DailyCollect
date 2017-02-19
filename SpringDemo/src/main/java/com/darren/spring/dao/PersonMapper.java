package com.darren.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.darren.spring.entity.Person;
@Repository("personMapper")
public interface PersonMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
    List<Person> selectAll();
}