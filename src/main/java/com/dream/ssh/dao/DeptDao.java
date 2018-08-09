package com.dream.ssh.dao;

import java.util.List;

import com.dream.ssh.po.Dept;

public interface DeptDao {

	List<Dept> findAll(Integer start, Integer length, String search, String noDir);

	Long countAll();

	Long countAllBySerch(String search);

	Dept findById(Integer i);

	void delete(Dept po);


	void save(Dept po);

	List<Dept> findAll();

	void update(Dept po);

}
