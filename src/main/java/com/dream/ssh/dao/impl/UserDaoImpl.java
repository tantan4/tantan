package com.dream.ssh.dao.impl;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.dream.ssh.dao.UserDao;
import com.dream.ssh.po.User;
@Repository
/**
 * 用户dao
 * @author tan
 *
 */
public class UserDaoImpl implements UserDao {
	private final static Logger LOG = LogManager.getLogger(UserDaoImpl.class);
	@Resource
	private SessionFactory sessionFactory;
	@Override
	public List<User> getUser(User u) {
		Query<User> query = sessionFactory.getCurrentSession().createQuery("from User where name=:name and password=:password", User.class);
		query.setParameter("name", u.getName());
		query.setParameter("password", u.getPassword());
		List<User> list = query.list();
		
		return list;
	}
	/**
	 * 根据登录名查询用户
	 */
	@Override
	public User getUserByLoginName(String loginName) {
		String hql="from User where loginName=:loginName";
		return sessionFactory.getCurrentSession().createQuery(hql, User.class).setParameter("loginName", loginName).uniqueResult();
	}
	/**
	 * 查询所有用户
	 */
	@Override
	public List<User> getAll() {
		
		List<User> list = sessionFactory.getCurrentSession().createQuery("from User", User.class).list();
		
		return list;
	}
	/**
	 * 根据id查找
	 */
	@Override
	public User findById(Integer id) {
		return sessionFactory.getCurrentSession().get(User.class, id);
	}
	/**
	 * 更新用户
	 */
	@Override
	public void update(User po) {
		sessionFactory.getCurrentSession().update(po);
	}
	/**
	 *  添加用户
	 */
	@Override
	public void save(User po) {
		sessionFactory.getCurrentSession().save(po);
	}
	/**
	 * 删除用户
	 */
	@Override
	public void delete(Integer i) {
		User u = findById (i);
		sessionFactory.getCurrentSession().delete(u);
	}
	/**
	 * 搜索
	 */
	@Override
	public List<User> findAll(Integer start, Integer length, String search, String loginDir) {
		String hql = "from User u where u.name like :name or u.loginName like :name order by u.loginName " + loginDir;
		return sessionFactory.getCurrentSession().createQuery(hql, User.class).setParameter("name", "%" + search + "%")
				.setFirstResult(start).setMaxResults(length).list();
	}
	/**
	 * 统计总数
	 */
	@Override
	public Long countAll() {
		String hql="select count(*) from User u";
		
		return (Long)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}
	/**
	 * 统计搜索的总数
	 */
	@Override
	public Long countAllBySerch(String search) {
		String hql = "select count(*) from User u where u.name like :name or u.loginName like :name";
		Long count = (Long) sessionFactory.getCurrentSession().createQuery(hql).setParameter("name", "%" + search + "%")
				.uniqueResult();
		return count;
	}
}
