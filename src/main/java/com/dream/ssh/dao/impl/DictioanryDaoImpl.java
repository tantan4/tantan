package com.dream.ssh.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dream.ssh.dao.DictionaryDao;
import com.dream.ssh.po.Dictionary;

@Repository
public class DictioanryDaoImpl implements DictionaryDao {
	private final static Logger LOG = LogManager.getLogger(DictioanryDaoImpl.class);

	@Resource
	private SessionFactory sessionFactory;

	@Override
	public Dictionary findByTypeAndValue(String string, String value) {
		String hql = "from Dictionary d where d.type = :type and d.value = :value";
		return sessionFactory.getCurrentSession().createQuery(hql, Dictionary.class).setParameter("type", string)
				.setParameter("value", value).uniqueResult();
	}

	@Override
	public List<Dictionary> findAllByType(String string) {
		String hql = "from Dictionary d where d.type = :type";
		return sessionFactory.getCurrentSession().createQuery(hql, Dictionary.class).setParameter("type", string)
				.list();
	}

	@Override
	public List<Dictionary> findAllBySearch(Integer start, Integer length, String search, String typeDir) {
		String hql = "from Dictionary d where d.type like :search or d.name like :search order by d.type " + typeDir;
		return sessionFactory.getCurrentSession().createQuery(hql, Dictionary.class).setParameter("search", "%" + search + "%")
				.setFirstResult(start).setMaxResults(length).list();
	}

	@Override
	public Long countAllBySearch(String search) {
		String hql = "select count(*) from Dictionary d where d.type like :search or d.name like :search";
		return (Long)sessionFactory.getCurrentSession().createQuery(hql).setParameter("search", "%" + search + "%").uniqueResult();
	}

	@Override
	public void create(Dictionary po) {
		this.sessionFactory.getCurrentSession().save(po);
	}

	@Override
	public Dictionary findByTypeAndName(String type, String name) {
		String hql = "from Dictionary d where d.type = :type and d.name = :name";
		return sessionFactory.getCurrentSession().createQuery(hql, Dictionary.class).setParameter("type", type)
				.setParameter("name", name).uniqueResult();
	}
}
