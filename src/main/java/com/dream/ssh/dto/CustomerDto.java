package com.dream.ssh.dto;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.dream.ssh.po.SimpleOrder;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerDto {
	private final static Logger LOG = LogManager.getLogger(CustomerDto.class);
	private Integer id;
	
	
	private String name;
	
	private Integer age;
	
	private Date birthday;
	private String gender;

	private Set<SimpleOrder> orders = new HashSet<SimpleOrder>();
	private Integer number;
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<SimpleOrder> getOrders() {
		return orders;
	}

	public void setOrders(Set<SimpleOrder> orders) {
		this.orders = orders;
	}
	//@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", locale = "zh", timezone="GMT+8")
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", locale = "zh", timezone="GMT+8")
	public Date getBirthday() {
		return birthday;
}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "CustomerDto [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", orders=" + orders
				+ ", number=" + number + "]";
	}
	
}
