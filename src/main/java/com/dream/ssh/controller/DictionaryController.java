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

import com.dream.ssh.dto.DictionaryDto;
import com.dream.ssh.service.DictionaryService;
import com.dream.ssh.vo.DataTable;
import com.dream.ssh.vo.MyResult;

@Controller
@RequestMapping("/dictionary")
@SessionAttributes("loginUser")
public class DictionaryController {
	private final static Logger LOG = LogManager.getLogger(DictionaryController.class);
	
	@RequestMapping
	public String toHome (Map<String, Object> map) {
		return "dictionary/page";
	}
	
	@Resource
	private DictionaryService dictionaryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public DataTable<DictionaryDto> list(Integer draw, Integer start, Integer length, @RequestParam("search[value]") String search,
			@RequestParam("order[0][dir]") String typeDir) {
		DataTable<DictionaryDto> data = dictionaryService.findAllBySearch(start, length, search, typeDir);
		data.setDraw(++draw);
		return data;
	}
	
	@RequestMapping(value= "/create", method = RequestMethod.GET)
	public String create (Map<String,Object>map) {
		return "dictionary/create";
	}
	
	@RequestMapping(value= "/create", method = RequestMethod.POST)
	@ResponseBody
	public MyResult create (DictionaryDto dto) {
		this.dictionaryService.create(dto);
		return new MyResult().setSuccess(true).setMessage("创建数据字典成功！");
	}
}
