package com.dream.ssh.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dream.ssh.dao.MenuDao;
import com.dream.ssh.dao.RoleDao;
import com.dream.ssh.dao.UserDao;
import com.dream.ssh.dto.MenuDto;
import com.dream.ssh.dto.UserDto;
import com.dream.ssh.po.Menu;
import com.dream.ssh.po.Role;
import com.dream.ssh.po.User;
import com.dream.ssh.service.MenuService;
import com.dream.ssh.vo.DataTable;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	private final static Logger LOG = LogManager.getLogger(MenuServiceImpl.class);
	@Resource
	private MenuDao menuDao;
	@Resource
	private UserDao userDao;
	@Override
	public List<MenuDto> findAllByTopMenu(UserDto userDto) {
		User user = userDao.findById(userDto.getId());
		// 当前用户有权限的菜单集合
		Set<Menu> userMenus = new HashSet<Menu>();
		Boolean isAdmin = false;
		for (Role r : user.getRoles()) {
			if (r.getName().equals("admin")) {
				isAdmin = true;
			}
			userMenus.addAll(r.getMenus());
		}
		List<Menu> pos = new ArrayList<Menu>();
		List<MenuDto> dtos = new ArrayList<MenuDto>();
		if (isAdmin) {
			pos = menuDao.findAllByParentIdIsNull();
			for (Menu po : pos) {
				MenuDto dto = new MenuDto(po);
				dtos.add(dto);
				
			}
		} else {
			// 添加顶级菜单到pos
			for (Menu m : userMenus) {
				if (m.getParent() == null) {
					pos.add(m);
					m.setChildren(new ArrayList<Menu>());
				}
			}
			// 为pos中的顶级菜单添加子菜单
			for (Menu m : userMenus) {
				if (m.getParent() != null) {
					m.getParent().getChildren().add(m);
				}
			}
			dtos = MenuDto.getDtos(pos, true);
		}
		// 构造dto，并设置第一顶级菜单及其第一个子菜单为active
		if (dtos.size() > 0) {
			dtos.get(0).setActive(true);
			
			if (null!=dtos.get(0).getChildren()&&dtos.get(0).getChildren().size()>0) {
				dtos.get(0).getChildren().get(0).setActive(true);
			}
		}

		return dtos;
	}
	@Override
	public List<MenuDto> findAllByMenu(UserDto userDto) {
		
		User user = userDao.findById(userDto.getId());
		// 当前用户有权限的菜单集合
		Set<Menu> userMenus = new HashSet<Menu>();
		Boolean isAdmin = false;
		for (Role r : user.getRoles()) {
			if (r.getName().equals("admin")) {
				isAdmin = true;
			}
			userMenus.addAll(r.getMenus());
		}
		List<Menu> pos = new ArrayList<Menu>();
		List<MenuDto> dtos = new ArrayList<MenuDto>();
		if (isAdmin) {
			pos = menuDao.findAllByParentIdIsNull();
			for (Menu po : pos) {
				MenuDto dto = new MenuDto(po);
				dtos.add(dto);
				
			}
		} else {
			// 添加顶级菜单到pos
			for (Menu m : userMenus) {
				if (m.getParent() == null) {
					pos.add(m);
					m.setChildren(new ArrayList<Menu>());
				}
			}
			// 为pos中的顶级菜单添加子菜单
			for (Menu m : userMenus) {
				if (m.getParent() != null) {
					
					
					m.getParent().getChildren().add(m);
				}
			}
			dtos = MenuDto.getDtos(pos, true);
		}
		for(MenuDto dto :dtos){
			if("系统管理".equals(dto.getName())){
				dto.setActive(true);
				List<MenuDto> children = dto.getChildren();
				for(MenuDto dt:children){
					if("菜单".equals(dt.getName())){
						dt.setActive(true);
					}
				}
			}
		}
		
		return dtos;
	}

	/**
	 * 将List<Menu>转换为List<MenuDto>
	 * 
	 * @param pos
	 * @return
	 */
	private List<MenuDto> posToDtos(List<Menu> pos) {
		List<MenuDto> dtos = new ArrayList<MenuDto>();
		for (Menu po : pos) {
			MenuDto dto = new MenuDto();
			dto = menuToMenudto(dto, po);

			if (po.getParent() != null) {
				MenuDto parent = new MenuDto();
				parent = menuToMenudto(parent, po.getParent());
				dto.setParent(parent);
			}
			List<Menu> childrens = po.getChildren();
			if (childrens.size() > 0) {
				List<MenuDto> dtoss = new ArrayList<MenuDto>();
				for (Menu children : childrens) {
					MenuDto childrenDto = new MenuDto();
					childrenDto = menuToMenudto(childrenDto, children);
					dtoss.add(childrenDto);
				}
				dto.setChildren(dtoss);
			}
			dtos.add(dto);
		}
		return dtos;
	}

	/**
	 * 将Menu转换为MenuDto
	 * 
	 * @param dto
	 * @param po
	 * @return
	 */
	private MenuDto menuToMenudto(MenuDto dto, Menu po) {

		dto.setIcon(po.getIcon());
		dto.setName(po.getName());
		dto.setNo(po.getNo());
		dto.setId(po.getId());
		dto.setUrl(po.getUrl());
		return dto;
	}

	@Override
	public DataTable<MenuDto> findAllBySearch(Integer start, Integer length, String search, String noDir) {
		DataTable<MenuDto> data = new DataTable<MenuDto>();
		List<Menu> pos = menuDao.findAllBySearch(start, length, search, noDir);
		List<MenuDto> dtos = new ArrayList<MenuDto>();
		for (Menu po : pos) {
			MenuDto dto = new MenuDto();
			dto = menuToMenudto(dto, po);
			if (po.getParent() != null) {
				MenuDto parent = new MenuDto();
				parent = menuToMenudto(parent, po.getParent());
				dto.setParent(parent);
			
			}
			dto.setChildren(null);
			dto.setParent(null);
			dtos.add(dto);
		}
		Long countSearch = menuDao.countBySearch(search);
		Long count = menuDao.count();
		data.setRecordsFiltered(countSearch);
		data.setRecordsTotal(count);
		data.setData(dtos);
		return data;
	}

	@Override
	public void createChild(MenuDto m) {
		Menu po = new Menu();
		po.setIcon(m.getIcon());
		po.setName(m.getName());
		po.setNo(m.getNo());
		po.setUrl(m.getUrl());
		Menu parent = new Menu();
		parent.setId(m.getParent().getId());
		po.setParent(parent);
		menuDao.save(po);
	}

	
	@Override
	public MenuDto findById(Integer id) {
		Menu po=menuDao.findById(id);
		MenuDto dto = new MenuDto();
		return menuToMenudto(dto,po);
	}
	@Override
	public void update(MenuDto u) {
		Menu po=menuDao.findById(u.getId());
		po.setIcon(u.getIcon());
		po.setName(u.getName());
		po.setNo(u.getNo());
		po.setUrl(u.getUrl());
		menuDao.save(po);
	}
	@Override
	public void create(MenuDto u) {
		Menu po = new Menu();
		po.setIcon(u.getIcon());
		po.setName(u.getName());
		po.setNo(u.getNo());
		po.setUrl(u.getUrl());
		menuDao.save(po);
	}
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			Menu po=menuDao.findById(id);
			menuDao.delete(po);
		}
	}
	@Resource
	private RoleDao roleDao;
	@Override
	public List<MenuDto> findIndex() {
		// 当前用户有权限的菜单集合
		Role r=roleDao.findByName();
		List<Menu> pos = r.getMenus();
		List<Menu> poss=new ArrayList<Menu>();
		List<MenuDto> dtos = new ArrayList<MenuDto>();
			// 添加顶级菜单到pos
			for (Menu m : pos) {
				if (m.getParent() == null) {
					poss.add(m);
					m.setChildren(new ArrayList<Menu>());
				}
			}
			// 为pos中的顶级菜单添加子菜单
			for (Menu m : pos) {
				if (m.getParent() != null) {
					m.getParent().getChildren().add(m);
				}
			}
			dtos = MenuDto.getDtos(poss, true);
		
		// 构造dto，并设置第一顶级菜单及其第一个子菜单为active
		if (dtos.size() > 0) {
			dtos.get(0).setActive(true);
			if (null!=dtos.get(0).getChildren()&&dtos.get(0).getChildren().size()>0) {
				dtos.get(0).getChildren().get(0).setActive(true);
			}
		}

		return dtos;
	}
	@Override
	public String getMessage() {
		
		return "返回值了";
	}
	@Override
	public DataTable findAll() {
		DataTable<MenuDto> data = new DataTable<MenuDto>();
		List<Menu> pos = menuDao.findAll();
		List<MenuDto> dtos = new ArrayList<MenuDto>();
		for(Menu po:pos){
			MenuDto dto = new MenuDto();
			dto=menuToMenudto(dto,po);
			dtos.add(dto);
		}
		Long count = menuDao.count();
		data.setRecordsFiltered(count);
		data.setRecordsTotal(count);
		data.setData(dtos);
		return data;
	}
}
