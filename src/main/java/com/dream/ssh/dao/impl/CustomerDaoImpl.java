package com.dream.ssh.dao.impl;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.dream.ssh.dao.CustomerDao;
import com.dream.ssh.po.Customer;
/**
 * Customer的Dao
 * @author tan
 *
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {
	private final static Logger LOG = LogManager.getLogger(CustomerDaoImpl.class);
	@Resource
	private SessionFactory sessionFactory;
	@Override
	public List<Customer> getAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<Customer> query = session.createQuery("from Customer", Customer.class);
		/*query.setFirstResult(arg0);
		query.setMaxResults(arg0);*/
		
		return query.list();
	}
	@Override
	public Customer findById(Integer id) {
		return sessionFactory.getCurrentSession().get(Customer.class, id);
	}
	
	@Override
	public void save(Customer customer) {
		sessionFactory.getCurrentSession().save(customer);
	}
	@Override
	public void update(Customer ct) {
		sessionFactory.getCurrentSession().update(ct);
	}
	@Override
	public void delete(Customer cu) {
		sessionFactory.getCurrentSession().delete(cu);
	}
	@Override
	public List<Customer> findAll(Integer start, Integer length, String search, String nameDir) {
		String hql="from Customer c where c.name like :name  order by c.name "+nameDir;
		
		return sessionFactory.getCurrentSession().createQuery(hql, Customer.class).setParameter("name", "%"+ search +"%").setFirstResult(start).setMaxResults(length).list();
	}
	@Override
	public Long countAll(String search) {
		String hql="select count(*) from Customer c where c.name like :name";
		return (Long)sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", search).uniqueResult();
	}
	@Override
	public Long countAll() {
		String hql="select count(*) from Customer c";
		return (Long)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}
}
