package com.dream.ssh.service;

import java.util.List;

import com.dream.ssh.dto.DeptDto;
import com.dream.ssh.dto.UserDto;
import com.dream.ssh.vo.DataTable;

public interface DeptService {

	DataTable<DeptDto> findAllBySearch(Integer start, Integer length, String search, String noDir);

	void createChild(DeptDto m);

	DeptDto findById(Integer id);

	List<DeptDto> findAllByDept(UserDto m);

	void update(DeptDto u);

	void delete(Integer[] id);

	void create(DeptDto u);

	List<DeptDto> findAll();

	List<DeptDto> findAllDeptsWithSelected(Integer id);



}
