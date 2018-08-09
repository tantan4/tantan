package com.dream.ssh.dto;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.dream.ssh.po.Dept;
import com.dream.ssh.po.Position;
import com.dream.ssh.po.Staff;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PositionDto {
	private final static Logger LOG = LogManager.getLogger(PositionDto.class);
	private Integer id;
	private String name;

	private String description;

	private Integer level;
	private String deptName;
	private Integer deptId;
	private Dept dept;

	public PositionDto() {
	}

	public PositionDto(Position po) {
		this.id = po.getId();
		this.name = po.getName();
		this.description = po.getDescription();
		this.level = po.getLevel();
		this.dept = po.getDept();
		
		if (null != po.getDept()) {
			this.deptName = dept.getName();
			this.deptId=dept.getId();
		}

	}

	public Integer getId() {
		return id;
	}
	
	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@JsonIgnore
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}


	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
