package com.dream.ssh.service;

import java.util.List;

import com.dream.ssh.dto.CustomerDto;
import com.dream.ssh.vo.DataTable;

public interface CustomerService {

	List<CustomerDto> getAll();

	void create(CustomerDto c);

	void update(CustomerDto c);

	CustomerDto findById(Integer id);

	void delete(Integer[] id);

	DataTable findAllBySearch(Integer start, Integer length, String search, String loginDir);

}
