package com.dream.ssh.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dream.ssh.dao.RoleDao;
import com.dream.ssh.dao.UserDao;
import com.dream.ssh.dto.RoleDto;
import com.dream.ssh.po.Menu;
import com.dream.ssh.po.Role;
import com.dream.ssh.po.User;
import com.dream.ssh.service.RoleService;
import com.dream.ssh.vo.DataTable;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	private final static Logger LOG = LogManager.getLogger(RoleServiceImpl.class);

	@Resource
	private RoleDao roleDao;
	
	@Override
	public DataTable<RoleDto> findAllBySearch(Integer start, Integer length, String search, String nameDir) {
		List<Role> pos = roleDao.findAllBy (start, length, search, nameDir);
		Long countSearch = roleDao.countAllBy (search);
		Long count=roleDao.countAllBy();
		DataTable<RoleDto> dt = new DataTable<RoleDto> ();
		dt.setData(RoleDto.getDtos(pos));
		dt.setRecordsFiltered(countSearch);
		dt.setRecordsTotal(count);
		return dt;
	}

	@Override
	public void deleteByIds(Integer[] id) {
		for(Integer i:id){
			Role r=roleDao.findById(i);
			roleDao.delete(r);
		}
	}

	@Override
	public void create(RoleDto r, Integer[] menuIds) {
		Role po = new Role ();
		po.setDescription(r.getDescription());
		po.setName(r.getName());
		List<Menu> menus = new ArrayList<Menu>();
		for (Integer menuId : menuIds) {
			Menu m = new Menu();
			m.setId(menuId);
			menus.add(m);
		}
		po.setMenus(menus);
		roleDao.save (po);
		
	}

	@Override
	public List<RoleDto> findAll() {
		List<Role> pos=roleDao.findAllBy();
		
		return RoleDto.getDtos(pos);
	}

	@Override
	public void update(RoleDto r) {
		Role po=roleDao.findById(r.getId());
		po.setDescription(r.getDescription());
		po.setName(r.getName());
		List<Menu> menus = new ArrayList<Menu>();
		for (String idStr : r.getMenuIds().split(",")) {
			Integer id = Integer.parseInt(idStr);
			Menu m = new Menu ();
			m.setId(id);
			menus.add(m);
		}
		po.setMenus(menus);
		roleDao.update(po);
	}

	@Override
	public RoleDto findById(Integer id) {
		Role r=roleDao.findById(id);
		
		return new RoleDto(r);
	}
	@Resource
	private UserDao userDao;
	@Override
	public List<RoleDto> findAllRolesWithSelected(Integer id) {
		List<Role> pos = roleDao.findAllBy();
		User user = userDao.findById(id);
		List<Role> uRoles = user.getRoles();
		List<RoleDto> dtos = RoleDto.getDtos(pos);
		for(RoleDto dto:dtos){
			for(Role r:uRoles){
				if(dto.getId().equals(r.getId())){
					dto.setSelected(true);
				}
			}
		}
		
		return dtos;
	}
}
