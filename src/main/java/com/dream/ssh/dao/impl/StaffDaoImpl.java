package com.dream.ssh.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dream.ssh.dao.StaffDao;
import com.dream.ssh.po.Staff;
@Repository
public class StaffDaoImpl implements StaffDao {
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public List<Staff> findAll(Integer start, Integer length, String search, String noDir) {
		String hql = "from Staff u where u.name like :name  order by u.id " + noDir;
		return sessionFactory.getCurrentSession().createQuery(hql, Staff.class).setParameter("name", "%" + search + "%")
				.setFirstResult(start).setMaxResults(length).list();
	}

	@Override
	public Long countAll() {
		String hql = "select count(*) from Staff u";

		return (Long) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long countAllBySerch(String search) {
		String hql = "select count(*) from Staff u where u.name like :name ";
		Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", "%" + search + "%")
				.uniqueResult();
		return count;
	}

	@Override
	public Staff findById(Integer i) {
		return sessionFactory.getCurrentSession().get(Staff.class, i);
	}

	@Override
	public void delete(Staff po) {
		sessionFactory.getCurrentSession().delete(po);
	}

	@Override
	public void save(Staff po) {
		sessionFactory.getCurrentSession().save(po);
	}

	@Override
	public void update(Staff po) {
		sessionFactory.getCurrentSession().update(po);
	}

}
