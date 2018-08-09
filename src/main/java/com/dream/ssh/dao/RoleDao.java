package com.dream.ssh.dao;
import java.util.List;

import com.dream.ssh.po.Role;

public interface RoleDao {

	List<Role> findAllBy(Integer start, Integer length, String search, String nameDir);

	Long countAllBy(String search);

	Long countAllBy();
	
	Role findById(Integer i);


	void delete(Role po);

	List<Role> findAllBy();

	void update(Role po);

	Role findByName();

	void save(Role po);

}
