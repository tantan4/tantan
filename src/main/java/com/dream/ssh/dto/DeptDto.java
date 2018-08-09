package com.dream.ssh.dto;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.dream.ssh.po.Dept;
import com.dream.ssh.po.Menu;
import com.dream.ssh.po.Position;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class DeptDto {
	private final static Logger LOG = LogManager.getLogger(DeptDto.class);
	private Integer id;
	private String name;
	
	private String description;
	private String no;
	private  String parentName;
	private Dept parent;
	private List<Dept> children =new ArrayList<Dept>();
	private String positionIds = "";
	private String positionNames = "";
	private List<Position> positions=new ArrayList<Position>();
	public DeptDto() {
	}
	public DeptDto(Dept po) {
		this.id=po.getId();
		this.name=po.getName();
		this.description=po.getDescription();
		this.no=po.getNo();
		this.parent=po.getParent();
		if(null!=po.getParent()){
			this.parentName=parent.getName();
		}
		for (Position m : po.getPositions()) {
			if (!positionIds.equals("")) {
				positionIds += ",";
				positionNames += ",";
			}
			positionIds += m.getId();
			positionNames += m.getName();
		}
		
	}
	
	public String getPositionIds() {
		return positionIds;
	}
	public void setPositionIds(String positionIds) {
		this.positionIds = positionIds;
	}
	public String getPositionNames() {
		return positionNames;
	}
	public void setPositionNames(String positionNames) {
		this.positionNames = positionNames;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	@JsonIgnore
	public Dept getParent() {
		return parent;
	}
	public void setParent(Dept parent) {
		this.parent = parent;
	}
	@JsonIgnore
	public List<Dept> getChildren() {
		return children;
	}
	public void setChildren(List<Dept> children) {
		this.children = children;
	}
	@JsonIgnore
	public List<Position> getPositions() {
		return positions;
	}
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
	public static List<DeptDto> getDtos(List<Dept> pos) {
		List<DeptDto> dtos = new ArrayList<DeptDto>();
		for(Dept po:pos){
			DeptDto dto = new DeptDto(po);
			dtos.add(dto);
		}
		return dtos;
	}
	
}
