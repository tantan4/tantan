package com.dream.ssh.dao;

import java.util.List;

import com.dream.ssh.po.Staff;

public interface StaffDao {

	List<Staff> findAll(Integer start, Integer length, String search, String nameDir);

	Long countAll();

	Long countAllBySerch(String search);

	Staff findById(Integer i);

	void delete(Staff po);


	void update(Staff po);

	void save(Staff po);

}
