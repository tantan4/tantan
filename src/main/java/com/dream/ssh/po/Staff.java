package com.dream.ssh.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 人员
 * @author tan
 *
 */
@Entity
@Table(name = "t_staff")
public class Staff {
	private final static Logger LOG = LogManager.getLogger(Staff.class);
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(length=20)
	private String name;
	@Column(length=4)
	private String gender;
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	
	/**
	 * 作为登录名
	 */
	@Column(length=20)
	private String mobile;
	@Column(length=20)
	private String headImage;
	
	// 维护关系数据
	@ManyToMany
	@JoinTable(name = "t_staff_position", 
	           joinColumns = @JoinColumn(name = "staff_id"), 
	           inverseJoinColumns = @JoinColumn(name = "position_id"))
	private List<Position> positions = new ArrayList<Position>();
	
	// 维护关系数据，一对一
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
