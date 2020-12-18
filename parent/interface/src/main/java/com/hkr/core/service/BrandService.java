package com.hkr.core.service;

import java.util.List;

import cn.itcast.common.page.Pagination;

import com.hkr.core.pojo.product.Brand;
import com.hkr.core.pojo.product.BrandQuery;

public interface BrandService {
	//品牌列表查询
	public List<Brand> findBrandListByNameAndIsDisplay(String name,Integer isDisplay);
	//分页
	public Pagination findBrandPageByQuery(String name,Integer isDisplay,Integer pageNo);
	//修改
	public Brand findBrandById(Long id);
	//提交
	public void updateBrand(Brand brand);
	//批量删除
	public void delBrandAll(Long[] ids);
}
