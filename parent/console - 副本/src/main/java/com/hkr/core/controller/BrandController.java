package com.hkr.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hkr.core.pojo.product.Brand;
import com.hkr.core.pojo.product.BrandQuery;
import com.hkr.core.service.BrandService;

import cn.itcast.common.page.Pagination;


@Controller
@RequestMapping("/brand")
public class BrandController {
	@Autowired
	private  BrandService brandService;
	
	//查询品牌列表
	@RequestMapping("/list")
	public String list(String name, Integer isDisplay,Integer pageNo,Model model) throws Exception{
		/*List<Brand> brandList = brandService.findBrandListByNameAndIsDisplay(name, isDisplay);*/
		Pagination pagination = brandService.findBrandPageByQuery(name, isDisplay, pageNo);
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("name", name);
		model.addAttribute("isDisplay", isDisplay);
		model.addAttribute("pageNo", pageNo);
		
		return "/brand/list";		
	}
	//修改
	@RequestMapping("/doEdit")
	public String doEdit(Long id,Model model) throws Exception{
		Brand brand = brandService.findBrandById(id);
		model.addAttribute("brand", brand);
		/*System.out.println(brand.getId());*/
		return "brand/edit";
	}
	
	//提交
	@RequestMapping("/edit")
	public String Edit(Brand brand) throws Exception{
		brandService.updateBrand(brand);
		return "forward:list.action";
	}
	//批量删除
	@RequestMapping("/deleteAll")
	public String deleteAll(Long[] ids) throws Exception{
		if(ids !=null&& ids.length>0){
			brandService.delBrandAll(ids);
		}
		
		return "forward:list.action";
	}
	
	
	
	
	
}
