package com.dream.ssh.service;

import java.util.List;

import com.dream.ssh.dto.MenuDto;
import com.dream.ssh.dto.UserDto;
import com.dream.ssh.vo.DataTable;
/**
 * 菜单service接口
 * @author tan
 *
 */
public interface MenuService {
	/**
	 * 查询顶级菜单
	 * @param u 
	 * @return
	 */
	List<MenuDto> findAllByTopMenu(UserDto u);
	/**
	 * 根据条件查询菜单
	 * @param start
	 * @param length
	 * @param search
	 * @param noDir
	 * @return
	 */
	DataTable<MenuDto> findAllBySearch(Integer start, Integer length, String search, String noDir);
	/**
	 * 创建子菜单
	 * @param m
	 */
	void createChild(MenuDto m);
	List<MenuDto> findAllByMenu(UserDto u);
	MenuDto findById(Integer id);
	void update(MenuDto u);
	void create(MenuDto u);
	void delete(Integer[] id);
	List<MenuDto> findIndex();
	String getMessage();
	DataTable findAll();

}
