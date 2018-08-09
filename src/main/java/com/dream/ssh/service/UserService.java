package com.dream.ssh.service;

import java.util.List;

import com.dream.ssh.dto.RoleDto;
import com.dream.ssh.dto.UserDto;
import com.dream.ssh.po.User;
import com.dream.ssh.vo.DataTable;

public interface UserService {

	boolean login(User u);

	List<UserDto> findAll();

	UserDto authenticate(String username, String password);
	UserDto findById(Integer id);

	void update(UserDto u);

	void create(UserDto u, Integer[] roleIds);

	void delete(Integer[] id);

	void reset(UserDto u);

	DataTable<UserDto> findAllBySearch(Integer start, Integer length, String search, String loginDir);

	UserDto findByLoginName(String loginName, Integer id);

	void delete(Integer[] id, UserDto u);


}
