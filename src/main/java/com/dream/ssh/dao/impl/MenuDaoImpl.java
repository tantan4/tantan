package com.dream.ssh.dao.impl;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dream.ssh.dao.MenuDao;
import com.dream.ssh.po.Menu;
@Repository
public class MenuDaoImpl implements MenuDao{
	private final static Logger LOG = LogManager.getLogger(MenuDaoImpl.class);
	@Resource
	private SessionFactory sessionFactory;
	@Override
	public List<Menu> findAllByParentIdIsNull() {
		String hql="from Menu u where u.parent.id is null order by u.no asc";
		
		return sessionFactory.getCurrentSession().createQuery(hql,Menu.class).list();
	}
	@Override
	public List<Menu> findAllBySearch(Integer start, Integer length, String search,String noDir) {
		String hql="from Menu m where m.name like :name or m.no like :name order by m.no "+noDir;
		return sessionFactory.getCurrentSession().createQuery(hql,Menu.class)
				.setParameter("name", "%"+ search +"%").setFirstResult(start)
				.setMaxResults(length).list();
	}
	@Override
	public Long countBySearch(String search) {
		String hql = "select count(*) from Menu m where m.name like :name or m.no like :name";
		Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", "%" + search + "%")
				.uniqueResult();
		return count;
	}
	@Override
	public Long count() {
		
		return (Long) sessionFactory.getCurrentSession().createQuery("select count(*) from Menu m").uniqueResult();
	}
	@Override
	public void save(Menu po) {
		sessionFactory.getCurrentSession().save(po);
	}
	@Override
	public Menu findById(Integer id) {
		return sessionFactory.getCurrentSession().get(Menu.class, id);
	}
	@Override
	public void delete(Menu m) {
		sessionFactory.getCurrentSession().remove(m);
	}
	@Override
	public Menu findIndex() {
		return null;
	}
	@Override
	public List<Menu> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Menu",Menu.class).list();
	}
	@Override
	public void update(Menu po) {
		sessionFactory.getCurrentSession().update(po);;
	}
}
