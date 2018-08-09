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

import com.dream.ssh.dto.RoleDto;
import com.dream.ssh.dto.UserDto;
import com.dream.ssh.service.RoleService;
import com.dream.ssh.service.UserService;
import com.dream.ssh.vo.DataTable;
import com.dream.ssh.vo.MyResult;
@Controller
@RequestMapping("/user")
@SessionAttributes("loginUser")
public class UserController {
	private final static Logger LOG = LogManager.getLogger(UserController.class);

	@Resource // @Autowired
	private UserService userService;

	@RequestMapping
	public String toHome(Map<String, Object> map) {
		return "user/page";
	}

	@RequestMapping("/list")
	@ResponseBody
	public DataTable<UserDto> list(Integer draw, Integer start, Integer length, 
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String loginDir) {
		DataTable<UserDto> data = userService.findAllBySearch(start, length, search, loginDir);
		data.setDraw(++draw);
		return data;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Integer id, Map<String, Object> map) {
		UserDto u = userService.findById(id);
		List<RoleDto> roles = roleService.findAllRolesWithSelected(id);
		map.put("user", u);
		map.put("roles", roles);
		return "user/update";
	}
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public MyResult update(UserDto u,Map<String, Object> map) {
		userService.update(u);
		return new MyResult().setSuccess(true).setMessage("修改成功");
	}
	@Resource
	private RoleService roleService;
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Map<String,Object>map) {
		List<RoleDto> roles = roleService.findAll();
		map.put("roles", roles);
		return "user/create";
		
	}
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public MyResult create(UserDto u,Integer[] roleIds) {
		userService.create(u,roleIds);
		return new MyResult().setSuccess(true).setMessage("添加成功");
	}

	@RequestMapping("/delete")
	@ResponseBody
	public MyResult delete(Integer[] id, Map<String, Object>map) {
		UserDto u = (UserDto) map.get("loginUser");
		userService.delete(id, u);
		return new MyResult().setSuccess(true).setMessage("删除用户成功！");
	}
	@RequestMapping ("/loginname")
	@ResponseBody
	public MyResult authenticate (String loginName,Integer id) {
		userService.findByLoginName(loginName,id);
		return new MyResult().setSuccess(true).setMessage("验证成功");
		
	}
}
