package com.dream.ssh.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dream.ssh.dao.PositionDao;
import com.dream.ssh.po.Position;
import com.dream.ssh.po.Position;
@Repository
public class PositionDaoImpl implements PositionDao {
	@Resource
	private SessionFactory sessionFactory;
	@Override
	public Position findById(Integer id) {
		return sessionFactory.getCurrentSession().get(Position.class, id);
	}

	@Override
	public List<Position> findAll(Integer start, Integer length, String search, String noDir) {
		String hql = "from Position u where u.name like :name  order by u.level " + noDir;
		return sessionFactory.getCurrentSession().createQuery(hql, Position.class).setParameter("name", "%" + search + "%")
				.setFirstResult(start).setMaxResults(length).list();
	}

	@Override
	public Long countAll() {
		String hql = "select count(*) from Position u";

		return (Long) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();

	}

	@Override
	public Long countAllBySerch(String search) {
		String hql = "select count(*) from Position u where u.name like :name ";
		Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", "%" + search + "%")
				.uniqueResult();
		return count;
	}

	@Override
	public void update(Position position) {
		sessionFactory.getCurrentSession().update(position);
	}

	@Override
	public void save(Position po) {
		sessionFactory.getCurrentSession().save(po);
	}

	@Override
	public void delete(Position po) {
		sessionFactory.getCurrentSession().delete(po);
	}

}
