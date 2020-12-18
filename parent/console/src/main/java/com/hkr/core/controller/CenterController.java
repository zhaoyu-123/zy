package com.hkr.core.controller;
/*
 * CenterController主要做页面跳转
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/console")
public class CenterController{
	
	//去首页
	@RequestMapping("/index")
	public String index() throws Exception{	
		return "index";
	}
	//去顶端页面
	@RequestMapping("/top")
	public String top() throws Exception{	
		return "top";
	}
	//去主体页面
	@RequestMapping("/main")
	public String main() throws Exception{	
		return "main";
	}
	//去左侧页面
	@RequestMapping("/left")
	public String left() throws Exception{	
		return "left";
	}
	//去右侧页面
	@RequestMapping("/right")
	public String right() throws Exception{	
		return "right";
	}
	//跳转到商品主体页面
	@RequestMapping("/frame/product_main")
	public String frameProductMain() throws Exception{
		return "frame/product_main";
	}
	//跳转到商品左侧页面
	@RequestMapping("/frame/product_left")
	public String frameProductLeft() throws Exception{
		return "frame/product_left";
	}


}
