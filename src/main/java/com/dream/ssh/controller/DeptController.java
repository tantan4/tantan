package com.dream.ssh.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dream.ssh.dao.DeptDao;
import com.dream.ssh.dto.DeptDto;
import com.dream.ssh.dto.UserDto;
import com.dream.ssh.service.DeptService;
import com.dream.ssh.vo.DataTable;
import com.dream.ssh.vo.MyResult;

@Controller
@RequestMapping("/dept")
@SessionAttributes("loginUser")
public class DeptController {
	@Resource
	private DeptService deptService;
	@RequestMapping
	public String toHome(Map<String, Object> map){
		return "dept/page";
	}
	@Resource
	public DeptDao deptDao;
	@RequestMapping("/list")
	@ResponseBody
	@Transactional
	public  DataTable list(Integer draw,Integer start,Integer length,
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String noDir){
		DataTable data=deptService.findAllBySearch(start,length,search,noDir);
	
		data.setDraw(++draw);
		return data;
		
	}
	@ResponseBody
	@RequestMapping(value="/createChild",method=RequestMethod.POST)
	public MyResult createChild(DeptDto m){
		deptService.createChild(m);
		return new MyResult().setSuccess(true).setMessage("创建成功！");
	}
	@RequestMapping(value="/createChild",method=RequestMethod.GET)
	public String create(Integer id,Map<String,Object>map){
		DeptDto m = deptService.findById(id);
		map.put("dept", m);
		return "dept/createChild";
	}
	@RequestMapping(value = "/dept", method = RequestMethod.GET)
	public String dept(Map<String, Object> map) {
		// 检查session中是否有用户信息
		if (map.get("loginUser") == null) {
			return "redirect:/login";
		}
		UserDto m=(UserDto)map.get("loginUser");
		List<DeptDto> deptDtos=deptService.findAllByDept(m);
		map.put("depts", deptDtos);
		return "main";
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Integer id, Map<String, Object> map) {
		DeptDto m = deptService.findById(id);
		map.put("dept", m);
		return "dept/update";
	}
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public MyResult update(DeptDto u) {
		deptService.update(u);
		return new MyResult().setSuccess(true).setMessage("修改成功！");
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Map<String, Object> map) {
		return "dept/create";
	}
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public MyResult create(DeptDto u) {
		deptService.create(u);
		return new MyResult().setSuccess(true).setMessage("创建成功！");
	}

	@RequestMapping("/delete")
	@ResponseBody
	public MyResult delete(Integer[] id) {
		
			deptService.delete(id);
		return new MyResult().setSuccess(true).setMessage("删除成功");
	}
	@RequestMapping("/positions")
	public String select(){
		return "position/selectorPage";
	}
}
