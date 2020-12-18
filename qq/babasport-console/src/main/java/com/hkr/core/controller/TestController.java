package com.hkr.core.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hkr.core.pojo.TbTest;
import com.hkr.core.service.TbTestService;

@Controller
public class TestController {
	
	@Autowired
	private TbTestService tbService;
	
	@RequestMapping("/list")
	public String list() throws Exception{
		TbTest tb = new  TbTest();
		tb.setName("qwer");
		tb.setBirthday(new  Date());
		tbService.insertTbTest(tb);
		return "index2";
	}
}
