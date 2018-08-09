
package com.dream.ssh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dream.ssh.dao.DeptDao;
import com.dream.ssh.dao.PositionDao;
import com.dream.ssh.dto.DeptDto;
import com.dream.ssh.dto.UserDto;
import com.dream.ssh.po.Dept;
import com.dream.ssh.po.Position;
import com.dream.ssh.service.DeptService;
import com.dream.ssh.vo.DataTable;
@Service
@Transactional
public class DeptServiceImpl implements DeptService {
	@Resource
	private DeptDao deptDao;
	@Resource
	private PositionDao positionDao;
	@Override
	public DataTable<DeptDto> findAllBySearch(Integer start, Integer length, String search, String noDir) {
		DataTable<DeptDto> data = new DataTable<DeptDto>();
		List<Dept> pos= deptDao.findAll(start,length,search,noDir);
		List<DeptDto> dtos=new ArrayList<DeptDto>();
		for(Dept po :pos){
			DeptDto dto=new DeptDto(po);
			dtos.add(dto);
		}
		
		Long count=deptDao.countAll();
		Long countSearch=deptDao.countAllBySerch(search);
		data.setRecordsFiltered(countSearch);
		data.setRecordsTotal(count);
		data.setData(dtos);
		return data;
	}

	@Override
	public void createChild(DeptDto m) {
		Dept po=new Dept();
		po.setDescription(m.getDescription());
		po.setName(m.getName());
		po.setNo(m.getNo());
		Dept parent=new Dept();
		parent.setId(m.getParent().getId());
		po.setParent(parent);
		deptDao.save(po);
	}

	@Override
	public DeptDto findById(Integer id) {
		Dept po=deptDao.findById(id);
		DeptDto dto = new DeptDto(po);
		return dto;
	}

	@Override
	public List<DeptDto> findAllByDept(UserDto m) {
		return null;
	}

	@Override
	public void update(DeptDto u) {
		Dept po=deptDao.findById(u.getId());
		po.setDescription(u.getDescription());
		po.setName(u.getName());
		po.setNo(u.getNo());
		deptDao.save(po);
	}

	@Override
	public void delete(Integer[] id) {
		for(Integer i:id){
			Dept po=deptDao.findById(i);
			deptDao.delete(po);
		}
		
	}

	@Override
	public void create(DeptDto m) {
		Dept po=new Dept();
		po.setDescription(m.getDescription());
		po.setName(m.getName());
		po.setNo(m.getNo());
		deptDao.save(po);
	}

	@Override
	public List<DeptDto> findAll() {
		List<Dept> pos=deptDao.findAll();
		List<DeptDto> dtos=DeptDto.getDtos(pos);
		return dtos;
	}

	@Override
	public List<DeptDto> findAllDeptsWithSelected(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
