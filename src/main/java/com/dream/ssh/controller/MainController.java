package com.dream.ssh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.dream.ssh.dto.MenuDto;
import com.dream.ssh.dto.UserDto;
import com.dream.ssh.po.User;
import com.dream.ssh.service.MenuService;
import com.dream.ssh.service.UserService;

@Controller
@SessionAttributes("loginUser")
public class MainController {
	private final static Logger LOG = LogManager.getLogger(MainController.class);
	
	/**
	 * 登录表单加载
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(String error, Map<String, Object> map) {
		map.put("error", error);
		return "login";
	}

	@Resource // @Autowired
	private UserService userService;

	/**
	 * 登录表单提交
	 * 
	 * @param username
	 * @param password
	 * @param remember
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, String remember, Map<String, Object> map) {
		// 验证用户身份
		UserDto u = userService.authenticate(username, password);
		// 如果验证成功，则在session中设置用户信息
		map.put("loginUser", u);
		// 重定向到主页
		return "redirect:/";
	}

	/**
	 * 返回所有顶级菜单
	 * 
	 * @return
	 */
	private List<MenuDto> getMenus() {
		List<MenuDto> menus = new ArrayList<MenuDto>();
		// 构造系统管理顶级菜单及其子菜单
		MenuDto sys = new MenuDto();
		sys.setId(1);
		sys.setName("系统管理");
		sys.setIcon("fa-gears");
		sys.setActive(true);
		menus.add(sys);
		// 设置系统管理的子菜单
		MenuDto user = new MenuDto();
		user.setId(11);
		user.setName("用户");
		user.setIcon("fa-user");
		user.setUrl("/user");
		user.setActive(true);
		sys.getChildren().add(user);
		MenuDto role = new MenuDto();
		role.setId(12);
		role.setName("角色");
		role.setIcon("fa-users");
		role.setUrl("/role");
		sys.getChildren().add(role);

		// 构造部门管理顶级菜单及其子菜单
		MenuDto dept = new MenuDto();
		dept.setId(2);
		dept.setName("部门管理");
		dept.setIcon("fa-sitemap");
		menus.add(dept);
		// 设置部门管理的子菜单
		MenuDto department = new MenuDto();
		department.setId(20);
		department.setName("部门");
		department.setIcon("fa-sitemap");
		department.setUrl("/dept");
		dept.getChildren().add(department);
		// 构造客户管理顶级菜单及其子菜单
		MenuDto customer = new MenuDto();
		customer.setId(3);
		customer.setName("客户管理");
		customer.setIcon("fa-sitemap");
		menus.add(customer);
		// 设置客户管理的子菜单
		MenuDto c = new MenuDto();
		c.setId(30);
		c.setName("客户");
		c.setIcon("fa-sitemap");
		c.setUrl("/customer");
		customer.getChildren().add(c);
		return menus;
	}
	@Resource
	private MenuService menuService;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Map<String, Object> map) {
		// 检查session中是否有用户信息
		if (map.get("loginUser") == null) {
			return "redirect:/main";
		}
		UserDto u=(UserDto)map.get("loginUser");
		List<MenuDto> menuDtos=menuService.findAllByTopMenu(u);
		map.put("menus", menuDtos);
		return "main";
	}
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String mainIndex(Map<String, Object> map) {
		// 检查session中是否有用户信息
		List<MenuDto> menuDtos=menuService.findIndex();
		map.put("menus", menuDtos);
		return "main";
	}

	@RequestMapping("/logout")
	public String logout(Map<String, Object> map, SessionStatus status) {
		map.remove("loginUser");
		status.setComplete();
		return "redirect:/login";
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form() {
		return "form";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	@RequestMapping(value="/reset",method=RequestMethod.GET)
	public String reset (Map<String, Object> map) {
		UserDto u=(UserDto) map.get("loginUser");
		if (u == null) {
			return "redirect:/login";
		}
		return "user/reset";
	}
	@RequestMapping(value="/reset",method=RequestMethod.POST)
	public String reset (String password,Map<String, Object> map) {
		UserDto u=(UserDto) map.get("loginUser");
		u.setPassword(password);
		userService.reset(u);
		return "redirect:/login";
	}
}
