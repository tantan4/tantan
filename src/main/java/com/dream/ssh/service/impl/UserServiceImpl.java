package com.dream.ssh.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dream.ssh.dao.UserDao;
import com.dream.ssh.dto.RoleDto;
import com.dream.ssh.dto.UserDto;
import com.dream.ssh.po.Role;
import com.dream.ssh.po.User;
import com.dream.ssh.service.UserService;
import com.dream.ssh.util.SSHUtil;
import com.dream.ssh.vo.DataTable;
import com.dream.ssh.vo.MyResult;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	private final static Logger LOG = LogManager.getLogger(UserServiceImpl.class);
	@Resource
	private UserDao userDao;
	@Override
	public boolean login(User u) {
		List<User> list = userDao.getUser(u);
		if(list.size()>0){
			return true;
		}
		return false;
	}
	@Override
	public List<UserDto> findAll() {
		List<User> pos=userDao.getAll();
		return UserToUserdto(pos);
	}
	private List<UserDto> UserToUserdto(List<User> pos) {
		List<UserDto> list = new ArrayList<UserDto>();
		for (User user : pos) {
			
			UserDto userDto = new UserDto();
			userDto.setLoginName(user.getLoginName());
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setPassword(user.getPassword());
			list.add(userDto);
		}
		return list;
	}
	/**
	 * User封装UserDto
	 * @param user
	 * @return
	 */
	private UserDto userToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setLoginName(user.getLoginName());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());
		return userDto;
	}
	/**
	 * 用户登录
	 */
	@Override
	public UserDto authenticate(String loginName, String password) {
		User u = userDao.getUserByLoginName(loginName);
		if(u==null){
			throw new RuntimeException("登录名或者密码有误");
		}
		String md5 = SSHUtil.getMD5(u.getId().toString(), password);
		if(!u.getPassword().equals(md5)){
			throw new RuntimeException("登录名或者密码有误");
		}
		UserDto userDto = new UserDto();
		userDto.setLoginName(u.getLoginName());
		userDto.setId(u.getId());
		userDto.setName(u.getName());
		userDto.setPassword(u.getPassword());
		return userDto;
	}
	@Override
	public UserDto findById(Integer id) {
		User u = userDao.findById (id);
		return userToUserDto (u);
	}

	@Override
	public void update(UserDto u) {
		User po = userDao.findById(u.getId());
		po.setLoginName(u.getLoginName());
		po.setName(u.getName());
		List<Role> roles = new ArrayList<Role>();
		for (RoleDto r : u.getRoles()) {
			Role role = new Role ();
			role.setId(r.getId());
			roles.add(role);
		}
		po.setRoles(roles);
		userDao.update (po);
	}
	@Override
	public UserDto findByLoginName(String loginName,Integer id) {
		User u=userDao.getUserByLoginName(loginName);
		if(u.getId()!=id){
			if(null!=u){
				throw new RuntimeException("存在相同登录名");
			}
		}
		
		return userToUserDto(u);
	}
	
	@Override
	public void create(UserDto u,Integer[] roleIds) {
		// 生成po
		User po = new User ();
		po.setLoginName(u.getLoginName());
		po.setName(u.getName());
		po.setPassword(u.getPassword());
		List<Role> list = new ArrayList<Role>();
		for(Integer	id:roleIds) {
			Role role = new Role();
			role.setId(id);
			list.add(role);
		}
		po.setRoles(list);
		// 创建po
		userDao.save (po);
		// 修改密码为md5
		String md5 = SSHUtil.getMD5(po.getId().toString(), po.getPassword());
		po.setPassword(md5);
		userDao.update(po);
	}

	@Override
	public void delete(Integer[] id) {
		for (Integer i : id) {
			userDao.delete (i);
		}
	}
	@Override
	public void reset(UserDto u) {
		User user = userDao.findById(u.getId());
		user.setPassword( SSHUtil.getMD5(user.getId().toString(), u.getPassword()));
		userDao.update(user);;
	}
	@Override
	public DataTable<UserDto> findAllBySearch(Integer start, Integer length, String search, String loginDir) {
		DataTable<UserDto> data = new DataTable<UserDto>();
		List<User> pos=userDao.findAll(start,length,search,loginDir);
		
		data.setData(UserDto.getDtos(pos));
		Long count=userDao.countAll();
		Long countSearch=userDao.countAllBySerch(search);
		data.setRecordsFiltered(countSearch);
		data.setRecordsTotal(count);
		return data;
	}
	@Override
	public void delete(Integer[] id, UserDto u) {
		for (Integer i : id) {
			if (i.equals(u.getId())) {
				throw new RuntimeException ("当前用户不允许删除！");
			}
			userDao.delete (i);
		}
		
	}
	
	
}
