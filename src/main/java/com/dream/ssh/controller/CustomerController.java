package com.dream.ssh.controller;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dream.ssh.dto.CustomerDto;
import com.dream.ssh.service.CustomerService;
import com.dream.ssh.vo.DataTable;
import com.dream.ssh.vo.MyResult;
@Controller
@SessionAttributes("loginUser")
public class CustomerController {
	private final static Logger LOG = LogManager.getLogger(CustomerController.class);
	@Resource
	private CustomerService customerService;
	@RequestMapping(value="/customer",method=RequestMethod.GET)
	private String customer(Map<String,Object>map){
		if (map.get("loginUser") == null) {
			return "redirect:/login";
		}
		return "customer/page";
	}
	@RequestMapping("/customer/list")
	@ResponseBody
	public DataTable list(Integer draw, Integer start, Integer length, 
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String nameDir) {
		DataTable<CustomerDto> data = customerService.findAllBySearch(start, length, search, nameDir);
		data.setDraw(++draw);
		return data;
	}
	
	@RequestMapping(value = "/customer/update", method = RequestMethod.GET)
	public String update (Integer id, Map<String, Object>map) {
		if (map.get("loginUser") == null) {
			return "redirect:/login";
		}
		CustomerDto c = customerService.findById (id);
		map.put("customer", c);
		return "customer/update";
	}
	@ResponseBody
	@RequestMapping(value = "/customer/update", method = RequestMethod.POST)
	public MyResult update (CustomerDto c) {
		customerService.update(c);
		return new MyResult().setSuccess(true).setMessage("修改成功");
	}

	@RequestMapping(value = "/customer/create", method = RequestMethod.GET)
	public String create (Map<String,Object> map) {
		if (map.get("loginUser") == null) {
			return "redirect:/login";
		}
		return "customer/create";
	}
	@ResponseBody
	@RequestMapping(value = "/customer/create", method = RequestMethod.POST)
	public MyResult create (CustomerDto c) {
		customerService.create (c);
		return new MyResult().setSuccess(true).setMessage("添加成功");
	}
	
	@RequestMapping ("/customer/delete")
	@ResponseBody
	public MyResult delete (Integer[] id) {
		customerService.delete (id);
		return new MyResult().setSuccess(true).setMessage("删除成功");
	}
}
