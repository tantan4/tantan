package com.dream.ssh.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.ssh.dao.CustomerDao;
import com.dream.ssh.dto.CustomerDto;
import com.dream.ssh.po.Customer;
import com.dream.ssh.po.SimpleOrder;
import com.dream.ssh.service.CustomerService;
import com.dream.ssh.vo.DataTable;
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	private final static Logger LOG = LogManager.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerDao customerDao;
	@Override
	public List<CustomerDto> getAll() {
		List<Customer> list=customerDao.getAll();
		return customerToCustomerDto(list);
	}
	private List<CustomerDto> customerToCustomerDto(List<Customer> list) {
		List<CustomerDto> cds = new ArrayList<CustomerDto>();
		for(Customer c:list){
			CustomerDto cd = new CustomerDto();
			cd.setAge(c.getAge());
			cd.setGender(c.getGender());
			cd.setId(c.getId());
			cd.setName(c.getName());
			/*Set<SimpleOrder> orders = c.getOrders();
			for (SimpleOrder simpleOrder : orders) {
				cd.getOrders().add(simpleOrder);
			}*/
			//cd.setOrders(c.getOrders());
			cd.setBirthday(c.getBirthday());
			cd.setNumber(c.getOrders().size());
			cds.add(cd);
		}
		return cds;
	}
	@Override
	public void create(CustomerDto c) {
		Customer customer = new Customer();
		customer.setName(c.getName());
		customer.setAge(c.getAge());
		
		customer.setBirthday(c.getBirthday());
		customer.setGender(c.getGender());
		customerDao.save(customer);
	}
	@Override
	public void update(CustomerDto c) {
		
		Customer ct=customerDao.findById(c.getId());
		ct.setName(c.getName());
		ct.setAge(c.getAge());
		customerDao.update(ct);
	}
	@Override
	public CustomerDto findById(Integer id) {
		Customer c=customerDao.findById(id);
		CustomerDto cd = new CustomerDto();
		cd.setAge(c.getAge());
		cd.setGender(c.getGender());
		cd.setId(c.getId());
		/*Set<SimpleOrder> orders = c.getOrders();
		for (SimpleOrder simpleOrder : orders) {
			cd.getOrders().add(simpleOrder);
		}*/
		cd.setName(c.getName());
		
		//cd.setOrders(c.getOrders());
		cd.setBirthday(c.getBirthday());
		cd.setNumber(c.getOrders().size());
		return cd;
	}
	@Override
	public void delete(Integer[] id) {
		for(Integer i: id){
			Customer cu=customerDao.findById(i);
			customerDao.delete(cu);
			
		}
	}
	@Override
	public DataTable findAllBySearch(Integer start, Integer length, String search, String nameDir) {
		List<Customer> pos=customerDao.findAll(start,length,search,nameDir);
	
		Long countSearch=customerDao.countAll(search);
		Long count=customerDao.countAll();
		DataTable<CustomerDto> data = new DataTable<CustomerDto>();
		data.setRecordsFiltered(countSearch);
		data.setRecordsTotal(count);
		data.setData(customerToCustomerDto(pos));
		return data;
	}
	
	
}
