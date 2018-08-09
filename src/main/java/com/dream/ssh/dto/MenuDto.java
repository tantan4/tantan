package com.dream.ssh.dto;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.dream.ssh.po.Menu;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class MenuDto {
	
	private final static Logger LOG = LogManager.getLogger(MenuDto.class);
	
	private Integer id;
	
	private String name;
	
	private MenuDto parent;
	
	private List<MenuDto> children = new ArrayList<MenuDto>();
	
	private String url;
	
	private String icon;

	private Boolean active = false;
	
	private String no;
	public MenuDto() {}
	public MenuDto(Menu m) {
		this.id=m.getId();
		this.name=m.getName();
		this.url=m.getUrl();
		this.icon=m.getIcon();
		if(null!=m.getParent()) {
			//this.parent=new MenuDto(m.getParent());
		}else {
			this.parent=null;
		}
		if(null!=m.getChildren()) {
			List<Menu> pos=m.getChildren();
			List<MenuDto> dtos=list(pos);
			this.children=dtos;
		}else {
			this.children=null;
		}
	}
	public MenuDto (Menu m, Boolean loadChildren) {
		this.id = m.getId();
		this.name = m.getName();
		this.url = m.getUrl();
		this.icon = m.getIcon();
		this.no = m.getNo();
		if (m.getParent() != null) {
			this.parent = new MenuDto (m.getParent(), false);
		}
		if (loadChildren) {
			this.children = MenuDto.getDtos(m.getChildren(), false);
		}
		
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
	@JsonIgnore
	public MenuDto getParent() {
		return parent;
	}
	
	public void setParent(MenuDto parent) {
		this.parent = parent;
	}
	@JsonIgnore
	public List<MenuDto> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDto> children) {
		this.children = children;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public String getActiveCls () {
		if (active) {
			return "active";
		}
		return "";
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	public String getParentName () {
		if (this.parent == null) {
			return "";
		}
		return parent.getName();
	}
	public static List<MenuDto> list(List<Menu> pos){
		List<MenuDto> dtos = new ArrayList<MenuDto>();
		for(Menu po:pos) {
			MenuDto dto=new MenuDto(po);
			dtos.add(dto);
		}
		return dtos;
	}
	public static List<MenuDto> getDtos (List<Menu> pos, Boolean loadChildren) {
		List<MenuDto> dtos = new ArrayList<MenuDto>();
		for (Menu po : pos) {
			dtos.add(new MenuDto(po, loadChildren));
		}
		return dtos;
	}
	@Override
	public String toString() {
		return "MenuDto [id=" + id + ", name=" + name + ", url=" + url + ", icon=" + icon + ", active=" + active
				+ ", no=" + no + "]";
	}
	
}
