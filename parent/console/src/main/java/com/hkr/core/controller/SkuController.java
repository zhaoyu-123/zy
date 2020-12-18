package com.hkr.core.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONObject;
import com.hkr.core.pojo.product.Sku;
import com.hkr.core.service.SkuService;

@Controller
@RequestMapping("/sku")
public class SkuController {
	@Autowired
	private SkuService skuService;
	@RequestMapping("/list")
	public String list(Long productId ,Model model) throws Exception{
		
		List<Sku> skuList = skuService.findSkuListByProductId(productId);
		model.addAttribute("skuList", skuList);
		
		return "sku/list";
	}
	@RequestMapping("/update")
	public void update(Sku sku ,HttpServletResponse response) throws Exception{
		skuService.updateSku(sku);
		JSONObject jo= new JSONObject();
		jo.put("message", "保存成功！");
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(jo.toString());
	}
}

