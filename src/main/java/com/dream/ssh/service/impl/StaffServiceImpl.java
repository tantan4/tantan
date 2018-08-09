package com.dream.ssh.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dream.ssh.dao.DictionaryDao;
import com.dream.ssh.dao.StaffDao;
import com.dream.ssh.dao.UserDao;
import com.dream.ssh.dto.StaffDto;
import com.dream.ssh.dto.UserDto;
import com.dream.ssh.po.Dictionary;
import com.dream.ssh.po.Position;
import com.dream.ssh.po.Staff;
import com.dream.ssh.po.User;
import com.dream.ssh.util.SSHUtil;
import com.dream.ssh.vo.DataTable;

@Service
@Transactional
public class StaffServiceImpl implements com.dream.ssh.service.StaffService {
	@Resource
	private StaffDao staffDao;
	@Resource
	private DictionaryDao dictionaryDao;
	@Override
	public DataTable<StaffDto> findAllBySearch(Integer start, Integer length, String search, String nameDir) {
		DataTable<StaffDto> data = new DataTable<StaffDto>();
		List<Staff> pos= staffDao.findAll(start,length,search,nameDir);
		List<StaffDto> dtos=new ArrayList<StaffDto>();
		for(Staff po :pos){
			StaffDto dto=new StaffDto(po);
			Dictionary dictionary = dictionaryDao.findByTypeAndValue("gender",po.getGender());
			dto.setGender(dictionary.getName());
			dtos.add(dto);
		}
		
		Long count=staffDao.countAll();
		Long countSearch=staffDao.countAllBySerch(search);
		data.setRecordsFiltered(countSearch);
		data.setRecordsTotal(count);
		data.setData(dtos);
		return data;
	}

	@Override
	public StaffDto findById(Integer id) {
		Staff po=staffDao.findById(id);
		StaffDto dto=new StaffDto(po);
		Dictionary dictionary = dictionaryDao.findByTypeAndValue("gender",po.getGender());
		dto.setGender(dictionary.getName());
		return dto;
	}
	@Resource
	private UserDao userDao;
	@Override
	public void update(StaffDto s) {
		Staff po = staffDao.findById(s.getId());
		po.setBirthday(s.getBirthday());
		po.setGender(s.getGender());
		po.setHeadImage(s.getHeadImage());
		po.setMobile(s.getMobile());
		po.setName(s.getName());
		List<Position> list = new ArrayList<Position>();
		if(s.getPositionIds()!=""){
			String[] ids = s.getPositionIds().split(",");
			for(String id:ids) {
				Position position = new Position();
				position.setId(Integer.valueOf(id));
				list.add(position);
			}
			po.setPositions(list);
		}
		staffDao.update(po);
	}

	@Override
	public void create(StaffDto s) {
		Staff po = new Staff();
		po.setBirthday(s.getBirthday());
		po.setGender(s.getGender());
		po.setHeadImage(s.getHeadImage());
		po.setMobile(s.getMobile());
		po.setName(s.getName());
		User user = new User ();
		user.setLoginName(s.getMobile());
		user.setName(s.getName());
		user.setPassword("123456");
		List<Position> list = new ArrayList<Position>();
		if(s.getPositionIds()!=""){
			String[] ids = s.getPositionIds().split(",");
			for(String id:ids) {
				Position position = new Position();
				position.setId(Integer.valueOf(id));
				list.add(position);
			}
			po.setPositions(list);
		}
		
		// 创建po
		userDao.save (user);
		// 修改密码为md5
		String md5 = SSHUtil.getMD5(user.getId().toString(), user.getPassword());
		user.setPassword(md5);
		userDao.update(user);
		po.setUser(user);
		staffDao.save(po);
	}

	@Override
	public void delete(Integer[] id,UserDto u) {
		for(Integer i:id) {
		 Staff po=staffDao.findById(i);
		 if(po.getMobile().equals(u.getLoginName())) {
			 throw new RuntimeException("不能删除本用户");
		 }
		 staffDao.delete(po);
		}
	}

}
