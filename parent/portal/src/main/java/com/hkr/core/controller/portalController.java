package com.hkr.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class portalController {
	@RequestMapping("/")
	public String index() throws Exception{
		
		return "index";
	}

}
