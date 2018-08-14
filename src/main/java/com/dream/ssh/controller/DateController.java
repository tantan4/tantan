package com.dream.ssh.controller;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class DateController {
	private final static Logger LOG = LogManager.getLogger(DateController.class);
	@RequestMapping("/date")
	public String toDate(){
		return "date";
	}
}
