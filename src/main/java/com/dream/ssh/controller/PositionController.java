package com.dream.ssh.controller;
import java.util.List;
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

import com.dream.ssh.dto.DeptDto;
import com.dream.ssh.dto.PositionDto;
import com.dream.ssh.service.DeptService;
import com.dream.ssh.service.PositionService;
import com.dream.ssh.vo.DataTable;
import com.dream.ssh.vo.MyResult;
@Controller
@RequestMapping("/position")
@SessionAttributes("loginUser")
public class PositionController {
	private final static Logger LOG = LogManager.getLogger(PositionController.class);
	@Resource // @Autowired
	private PositionService positionService;

	@RequestMapping
	public String toHome(Map<String, Object> map) {
		return "position/page";
	}

	@RequestMapping("/list")
	@ResponseBody
	public DataTable<PositionDto> list(Integer draw, Integer start, Integer length, 
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String loginDir) {
		DataTable<PositionDto> data = positionService.findAllBySearch(start, length, search, loginDir);
		data.setDraw(++draw);
		return data;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Integer id, Map<String, Object> map) {
		PositionDto u = positionService.findById(id);
		List<DeptDto> depts = deptService.findAll();
		map.put("position", u);
		map.put("depts", depts);
		return "position/update";
	}
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public MyResult update(PositionDto u) {
		positionService.update(u);
		return new MyResult().setSuccess(true).setMessage("修改成功！");
	}
	@Resource
	private DeptService deptService;
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Map<String,Object>map) {
		List<DeptDto> depts = deptService.findAll();
		map.put("depts", depts);
		return "position/create";
	}
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public MyResult create(PositionDto u) {
		positionService.create(u);
		return new MyResult().setSuccess(true).setMessage("添加成功");
	}

	@RequestMapping("/delete")
	@ResponseBody
	public MyResult delete(Integer[] id, Map<String, Object>map) {
		positionService.delete(id);
		return new MyResult().setSuccess(true).setMessage("删除用户成功！");
	}
	@RequestMapping(value = "/dept", method = RequestMethod.GET)
	public String dept(Map<String, Object> map) {
		
		return "dept/selectorPage";
	}
	@RequestMapping(value = "/staff", method = RequestMethod.GET)
	public String staff(Map<String, Object> map) {
		
		return "staff/selectorPage";
	}
}
