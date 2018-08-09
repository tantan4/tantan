package com.dream.ssh.dao;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.dream.ssh.dto.UserDto;
import com.dream.ssh.po.User;

public interface UserDao {

	List<User> getUser(User u);
	User getUserByLoginName(String loginName);
	List<User> getAll();
	User findById(Integer id);

	void update(User po);


	void delete(Integer i);
	List<User> findAll(Integer start, Integer length, String search, String loginDir);
	Long countAll();
	Long countAllBySerch(String search);
	void save(User po);
	


}
