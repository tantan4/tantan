package com.dream.ssh.service;

import com.dream.ssh.dto.StaffDto;
import com.dream.ssh.dto.UserDto;
import com.dream.ssh.vo.DataTable;

public interface StaffService {

	DataTable<StaffDto> findAllBySearch(Integer start, Integer length, String search, String loginDir);

	StaffDto findById(Integer id);

	void update(StaffDto u);

	void create(StaffDto u);

	void delete(Integer[] id, UserDto u);

}
