package com.dream.ssh.dao;

import java.util.List;

import com.dream.ssh.po.Customer;

public interface CustomerDao {

	List<Customer> getAll();

	Customer findById(Integer id);

	void save(Customer customer);

	void update(Customer ct);

	void delete(Customer cu);

	List<Customer> findAll(Integer start, Integer length, String search, String nameDir);

	Long countAll(String search);

	Long countAll();

}
