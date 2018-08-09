package com.dream.ssh.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dream.ssh.dao.MenuDao;
import com.dream.ssh.dto.MenuDto;
import com.dream.ssh.dto.UserDto;
import com.dream.ssh.po.Menu;
import com.dream.ssh.service.MenuService;
import com.dream.ssh.vo.DataTable;
import com.dream.ssh.vo.MyResult;
@Controller
@RequestMapping("/menu")
@SessionAttributes("loginUser")
public class MenuController {
	private final static Logger LOG = LogManager.getLogger(MenuController.class);
	@Resource
	private MenuService menuService;
	@RequestMapping
	private String toHome(Map<String, Object> map){
		return "menu/page";
	}
	@Resource
	private MenuDao menuDao;
	@RequestMapping("/list")
	@ResponseBody
	@Transactional
	public  DataTable list(Integer draw,Integer start,Integer length,
			@RequestParam("search[value]") String search, @RequestParam("order[0][dir]") String noDir){
		DataTable data=menuService.findAllBySearch(start,length,search,noDir);
		//DataTable data=menuService.findAll();
	
		data.setDraw(++draw);
		return data;
		
	}
	@ResponseBody
	@RequestMapping(value="/createChild",method=RequestMethod.POST)
	public MyResult createChild(MenuDto m){
		menuService.createChild(m);
		return new MyResult().setSuccess(true).setMessage("创建成功！");
	}
	@RequestMapping(value="/createChild",method=RequestMethod.GET)
	public String create(Integer id,Map<String,Object>map){
		MenuDto m = menuService.findById(id);
		map.put("menu", m);
		return "menu/createChild";
	}
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(Map<String, Object> map) {
		// 检查session中是否有用户信息
		if (map.get("loginUser") == null) {
			return "redirect:/login";
		}
		UserDto m=(UserDto)map.get("loginUser");
		List<MenuDto> menuDtos=menuService.findAllByMenu(m);
		map.put("menus", menuDtos);
		return "main";
	}
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Integer id, Map<String, Object> map) {
		MenuDto m = menuService.findById(id);
		map.put("menu", m);
		return "menu/update";
	}
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public MyResult update(MenuDto u) {
		menuService.update(u);
		return new MyResult().setSuccess(true).setMessage("修改成功！");
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Map<String, Object> map) {
		return "menu/create";
	}
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public MyResult create(MenuDto u) {
		menuService.create(u);
		return new MyResult().setSuccess(true).setMessage("创建成功！");
	}

	@RequestMapping("/delete")
	@ResponseBody
	public MyResult delete(Integer[] id) {
		
			menuService.delete(id);
		return new MyResult().setSuccess(true).setMessage("删除成功");
	}
}
