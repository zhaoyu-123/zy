package com.hkr.core.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.itcast.common.page.Pagination;

import com.hkr.core.common.Constants;
import com.hkr.core.pojo.product.Brand;
import com.hkr.core.pojo.product.Color;
import com.hkr.core.pojo.product.Product;
import com.hkr.core.service.BrandService;
import com.hkr.core.service.ColorService;
import com.hkr.core.service.ProductService;
import com.hkr.core.service.UploadService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private BrandService brandService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ColorService colorService;
	
	
	@RequestMapping("/list")
	public String list(String name,Boolean isShow,Long brandId,Integer pageNo,Model model) throws Exception{
		List<Brand> brandList = brandService.findBrandListByNameAndIsDisplay(null, null);
		for (Brand brand : brandList) {
			System.out.println(brand);
		}
		Pagination pagination = productService.findProductPagination(name, brandId, isShow, pageNo);
		model.addAttribute("pagination",pagination);
		model.addAttribute("name", name);
		model.addAttribute("isShow", isShow);
		model.addAttribute("brandId", brandId);
		model.addAttribute("brandList", brandList);
		
		return "product/list";
		
	}
	
	@RequestMapping("/toAdd")
	public String toAdd(Model model)throws Exception{
		List<Brand> brandList = brandService.findBrandListByNameAndIsDisplay(null, null);
		List<Color> colorList = colorService.findColorList();
		
		model.addAttribute("brandList", brandList);
		model.addAttribute("colorList", colorList);
		return "product/add";
		
	}
	//商品管理_商品提交
	@RequestMapping("/add")
	public String add(Product product) throws Exception{
		productService.insertProduct(product);
		return "redirect:/product/list.action";
	}
	//商品管理_商品上架
	@RequestMapping("/isShow")
	public String isShow(Long[] ids) throws Exception{
		productService.isShow(ids);
		
		return "redirect:/product/list.action";
	}
	
	
}
