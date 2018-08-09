package com.dream.ssh.service;

import java.util.List;

import com.dream.ssh.dto.PositionDto;
import com.dream.ssh.dto.StaffDto;
import com.dream.ssh.vo.DataTable;

public interface PositionService {

	DataTable<PositionDto> findAllBySearch(Integer start, Integer length, String search, String loginDir);

	PositionDto findById(Integer id);

	void update(PositionDto u);

	void create(PositionDto u);

	void delete(Integer[] id);

	List<PositionDto> findAllPositionsWithSelected(Integer id);

	List<PositionDto> findAll();

	List<PositionDto> findAllPositionsWithSelected(StaffDto u);

}
