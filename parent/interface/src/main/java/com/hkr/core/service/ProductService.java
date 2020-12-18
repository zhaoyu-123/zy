package com.hkr.core.service;

import com.hkr.core.pojo.product.Product;

import cn.itcast.common.page.Pagination;

public interface ProductService {

	//去商品管理的列表页面
	public Pagination findProductPagination(String name,Long brandId,Boolean isShow,Integer pageNo)throws Exception;
	
	
	//商品管理_商品提交
	public void insertProduct(Product product);
	
	//商品管理_商品上架
	public void isShow(Long[] ids);
}
