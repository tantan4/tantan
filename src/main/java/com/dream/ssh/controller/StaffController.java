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

import com.dream.ssh.dto.DictionaryDto;
import com.dream.ssh.dto.PositionDto;
import com.dream.ssh.dto.StaffDto;
import com.dream.ssh.dto.UserDto;
import com.dream.ssh.service.DictionaryService;
import com.dream.ssh.service.PositionService;
import com.dream.ssh.service.StaffService;
import com.dream.ssh.vo.DataTable;
import com.dream.ssh.vo.MyResult;
@Controller
@RequestMapping("/staff")
@SessionAttributes("loginUser")
public class StaffController {
	private final static Logger LOG = LogManager.getLogger(StaffController.class);
	@Resource // @Autowired
	private StaffService staffService;

	@RequestMapping
	public String toHome(Map<String, Object> map) {
		return "staff/page";
	}

	@RequestMapping("/list")
	@ResponseBody
	public DataTable<StaffDto> list(Integer draw, Integer start, Integer length, 
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String loginDir) {
		DataTable<StaffDto> data = staffService.findAllBySearch(start, length, search, loginDir);
		data.setDraw(++draw);
		return data;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Integer id, Map<String, Object> map) {
		StaffDto u = staffService.findById(id);
		List<DictionaryDto> dictionarys=dictionaryService.findAllByTypeWithSelected("gender",u);
		map.put("staff", u);
		map.put("dictionarys", dictionarys);
		return "staff/update";
	}
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public MyResult update(StaffDto u) {
		staffService.update(u);
		return new MyResult().setSuccess(true).setMessage("修改成功");
	}
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private PositionService positionService;
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Map<String,Object>map) {
		List<PositionDto> positions = positionService.findAll();
		List<DictionaryDto> dictionarys=dictionaryService.findAllByType("gender");
		map.put("positions", positions);
		map.put("dictionarys", dictionarys);
		return "staff/create";
	}
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public MyResult create(StaffDto u) {
		staffService.create(u);
		return new MyResult().setSuccess(true).setMessage("添加成功");
	}

	@RequestMapping("/delete")
	@ResponseBody
	public MyResult delete(Integer[] id, Map<String, Object>map) {
		UserDto u = (UserDto) map.get("loginUser");
		staffService.delete(id, u);
		return new MyResult().setSuccess(true).setMessage("删除用户成功！");
	}
	@RequestMapping("/positions")
	public String postions(Map<String, Object> map) {
		return "position/selectorPage";
	}
}
