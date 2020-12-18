package com.hkr.core.dao.product;

import java.util.List;

import com.hkr.core.pojo.product.Brand;
import com.hkr.core.pojo.product.BrandQuery;

public interface BrandDao {
	//品牌列表查询
	public List<Brand> findBrandByNameAndIsDisplay(BrandQuery brandQuery);
	//分页
	public List<Brand> findBrandPageByQuery(BrandQuery brandQuery); 
	//分页总体条数
	public Integer findBrandPageByQueryCount(BrandQuery brandQuery);
	//修改
	public Brand findBrandById(Long id);
	//提交
	public void updateBrand(Brand brand);
	//批量删除
	public void delBrandAll(Long[] ids);
}
