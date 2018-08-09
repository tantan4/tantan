package com.dream.ssh.dto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.dream.ssh.po.Position;
import com.dream.ssh.po.Staff;
import com.dream.ssh.po.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class StaffDto {
	private final static Logger LOG = LogManager.getLogger(StaffDto.class);
	private Integer id;
	private String name;
	private String gender;
	private Date birthday;
	private String positionIds="";
	/**
	 * 作为登录名
	 */
	private String mobile;
	private String headImage;
	
	private List<Position> positions =new ArrayList<Position>();
	private String positionNames="";
	private User user;
	public StaffDto() {
	}
	public StaffDto(Staff po) {
		this.id=po.getId();
		this.name=po.getName();
		this.birthday=po.getBirthday();
		this.mobile=po.getMobile();
		this.headImage=po.getHeadImage();
		this.positions=po.getPositions();
		if(positions.size()==0){
			this.positionNames=positionNames;
		}else{
			for(Position p:positions){
				if(!positionNames.equals("")){
					positionNames+=",";
					positionIds+=",";
				}
				positionNames+=p.getName();
				positionIds+=p.getId();
				
			}
			this.positionNames=positionNames;
		}
		
		
	}

	public String getPositionIds() {
		return positionIds;
	}
	public void setPositionIds(String positionIds) {
		this.positionIds = positionIds;
	}
	public Integer getId() {
		return id;
	}
	
	public String getPositionNames() {
		
		return positionNames;
	}
	public void setPositionNames(String positionNames) {
		this.positionNames = positionNames;
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
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
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
	@JsonIgnore
	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
