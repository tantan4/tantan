package com.dream.ssh.dao;

import java.util.List;

import com.dream.ssh.po.Menu;

/**
 * 菜单接口
 * 
 * @author tan
 *
 */
public interface MenuDao {
	/**
	 * 查询顶级菜单
	 * 
	 * @return
	 */
	List<Menu> findAllByParentIdIsNull();

	/**
	 * 根据条件查询
	 * 
	 * @param start
	 * @param length
	 * @param search
	 * @return
	 */
	List<Menu> findAllBySearch(Integer start, Integer length, String search,String noDir);

	/**
	 * 根据条件查询总数
	 * 
	 * @param search
	 * @return
	 */
	Long countBySearch(String search);

	/**
	 * 查询总数
	 * 
	 * @return
	 */
	Long count();
	 /**
	  * 创建菜单
	  * @param po
	  */

	Menu findById(Integer id);

	void delete(Menu po);

	Menu findIndex();

	List<Menu> findAll();

	void save(Menu po);

	void update(Menu po);

	

}
