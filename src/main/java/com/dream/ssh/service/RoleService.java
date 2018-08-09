package com.dream.ssh.service;

import java.util.List;

import com.dream.ssh.dto.RoleDto;
import com.dream.ssh.vo.DataTable;

public interface RoleService {

	DataTable<RoleDto> findAllBySearch(Integer start, Integer length, String search, String nameDir);

	void deleteByIds(Integer[] id);

	void create(RoleDto r, Integer[] menuIds);

	List<RoleDto> findAll();

	void update(RoleDto r);

	RoleDto findById(Integer id);

	List<RoleDto> findAllRolesWithSelected(Integer id);

}
