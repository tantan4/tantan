package com.dream.ssh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dream.ssh.dao.PositionDao;
import com.dream.ssh.dto.PositionDto;
import com.dream.ssh.dto.StaffDto;
import com.dream.ssh.po.Dept;
import com.dream.ssh.po.Position;
import com.dream.ssh.po.Staff;
import com.dream.ssh.service.PositionService;
import com.dream.ssh.vo.DataTable;
@Service
@Transactional
public class PositionServiceImpl implements PositionService {
	@Resource
	private PositionDao positionDao;
	@Override
	public DataTable<PositionDto> findAllBySearch(Integer start, Integer length, String search, String noDir) {
		DataTable<PositionDto> data = new DataTable<PositionDto>();
		List<Position> pos= positionDao.findAll(start,length,search,noDir);
		List<PositionDto> dtos=new ArrayList<PositionDto>();
		for(Position po :pos){
			PositionDto dto=new PositionDto(po);
			dtos.add(dto);
		}
		
		Long count=positionDao.countAll();
		Long countSearch=positionDao.countAllBySerch(search);
		data.setRecordsFiltered(countSearch);
		data.setRecordsTotal(count);
		data.setData(dtos);
		return data;
	}

	@Override
	public PositionDto findById(Integer id) {
		Position po=positionDao.findById (id);
		PositionDto dto=new PositionDto(po);
		return dto;
	}

	@Override
	public void update(PositionDto dto) {
		Position po = positionDao.findById(dto.getId());
		Dept dept = new Dept();
		dept.setId(dto.getDeptId());
		po.setLevel(dto.getLevel());
		po.setDescription(dto.getDescription());
		po.setName(dto.getName());
		po.setDept(dept);
		positionDao.update(po);
	}

	@Override
	public void create(PositionDto dto) {
		Position po = new Position();
		Dept dept = new Dept();
		dept.setId(dto.getDeptId());
		Staff staff = new Staff();
		po.setLevel(dto.getLevel());
		po.setDescription(dto.getDescription());
		po.setName(dto.getName());
		
		po.setDept(dept);
		positionDao.save(po);
		
	}

	@Override
	public void delete(Integer[] id) {
		for(Integer i:id) {
			Position po = positionDao.findById(i);
			positionDao.delete(po);
		}
	}

	@Override
	public List<PositionDto> findAllPositionsWithSelected(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PositionDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PositionDto> findAllPositionsWithSelected(StaffDto u) {
		List<Position> positions = u.getPositions();
		
		return null;
	}

}
