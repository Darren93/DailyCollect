package com.darren.spring.vo;

import java.io.Serializable;

import com.darren.spring.utils.XMLMapping;
@XMLMapping("PersonVO")
public class PersonVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XMLMapping("name")
	private String name ;
	@XMLMapping("gender")
	private String gender;
	@XMLMapping("address")
	private String address;
	@XMLMapping("id")
	private int id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public PersonVO() {
		super();
	}
	public PersonVO(String name, String gender, String address, int id) {
		super();
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.id = id;
	}
}
