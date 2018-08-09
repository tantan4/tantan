package com.dream.ssh.po;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
/**
 * 部门
 * @author tan
 *
 */
@Entity
@Table (name = "t_dept")
public class Dept {
	private final static Logger LOG = LogManager.getLogger(Dept.class);
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(length=20)
	private String name;
	
	private String description;
	@Column(length=10)
	private String no;
	
	// 维护关系数据
	@ManyToOne
	@JoinColumn(name="parent_id")
	private Dept parent;
	@OneToMany(mappedBy="parent")
	private List<Dept> children =new ArrayList<Dept>();
	@OneToMany(mappedBy="dept")
	private List<Position> positions=new ArrayList<Position>();
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
	public Dept getParent() {
		return parent;
	}
	public void setParent(Dept parent) {
		this.parent = parent;
	}
	public List<Dept> getChildren() {
		return children;
	}
	public void setChildren(List<Dept> children) {
		this.children = children;
	}
	public List<Position> getPositions() {
		return positions;
	}
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
	
}
