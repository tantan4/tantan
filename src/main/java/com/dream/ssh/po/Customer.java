package com.dream.ssh.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "customer")
/**
 * 客户实体类
 * 
 * @author tan
 *
 */
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c_id")
	private Integer id;

	@Column(name = "c_name", length = 20)
	private String name;

	@Column(name = "c_age")
	private Integer age;

	@Column(name = "c_gender", length = 5)
	private String gender;
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<SimpleOrder> orders = new HashSet<SimpleOrder>();

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
	
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + "]";
	}

}
