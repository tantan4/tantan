package com.dream.ssh.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dream.ssh.dao.RoleDao;
import com.dream.ssh.po.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
	private final static Logger LOG = LogManager.getLogger(RoleDaoImpl.class);

	@Resource
	private SessionFactory sessionFactory;

	@Override
	public List<Role> findAllBy(Integer start, Integer length, String search, String nameDir) {
		String hql = "from Role r where r.name like :name order by r.name " + nameDir;
		return sessionFactory.getCurrentSession().createQuery(hql, Role.class).setParameter("name", "%" + search + "%")
				.setFirstResult(start).setMaxResults(length).list();
	}

	@Override
	public Long countAllBy(String search) {
		String hql = "select count(*) from Role r where r.name like :name";
		return (Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", "%" + search + "%")
				.uniqueResult();
	}

	@Override
	public Role findById(Integer i) {
		return sessionFactory.getCurrentSession().get(Role.class, i);
	}

	@Override
	public void delete(Role r) {
		sessionFactory.getCurrentSession().delete(r);
	}

	@Override
	public Long countAllBy() {
		String hql = "select count(*) from Role r";
		return (Long) sessionFactory.getCurrentSession().createQuery(hql)
				.uniqueResult();
	}

	@Override
	public void save(Role po) {
		sessionFactory.getCurrentSession().save(po);
	}

	@Override
	public List<Role> findAllBy() {
		return sessionFactory.getCurrentSession().createQuery("from Role", Role.class).list();
	}

	@Override
	public void update(Role po) {
		sessionFactory.getCurrentSession().update(po);
	}

	@Override
	public Role findByName() {
		return sessionFactory.getCurrentSession().createQuery("from Role r where r.name='visitor'", Role.class).uniqueResult();
	}
	
}
