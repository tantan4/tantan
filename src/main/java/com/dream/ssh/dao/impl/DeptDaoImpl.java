package com.dream.ssh.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dream.ssh.dao.DeptDao;
import com.dream.ssh.po.Dept;
import com.dream.ssh.po.Dept;

@Repository
public class DeptDaoImpl implements DeptDao {
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public List<Dept> findAll(Integer start, Integer length, String search, String noDir) {
		String hql = "from Dept u where u.name like :name  order by u.no " + noDir;
		return sessionFactory.getCurrentSession().createQuery(hql, Dept.class).setParameter("name", "%" + search + "%")
				.setFirstResult(start).setMaxResults(length).list();
	}

	@Override
	public Long countAll() {
		String hql = "select count(*) from Dept u";

		return (Long) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long countAllBySerch(String search) {
		String hql = "select count(*) from Dept u where u.name like :name ";
		Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", "%" + search + "%")
				.uniqueResult();
		return count;
	}

	@Override
	public Dept findById(Integer i) {
		return sessionFactory.getCurrentSession().get(Dept.class, i);
	}

	@Override
	public void delete(Dept po) {
		sessionFactory.getCurrentSession().delete(po);
	}

	@Override
	public void save(Dept po) {
		sessionFactory.getCurrentSession().save(po);
	}

	@Override
	public List<Dept> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Dept d", Dept.class).list();
	}

	@Override
	public void update(Dept po) {
		sessionFactory.getCurrentSession().update(po);
	}

}
