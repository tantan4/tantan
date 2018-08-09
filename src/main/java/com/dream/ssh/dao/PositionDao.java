package com.dream.ssh.dao;

import java.util.List;

import com.dream.ssh.po.Position;

public interface PositionDao {

	Position findById(Integer id);

	List<Position> findAll(Integer start, Integer length, String search, String noDir);

	Long countAll();

	Long countAllBySerch(String search);

	void update(Position position);

	void save(Position po);

	void delete(Position po);

}
