package com.hkr.core.service;

import java.util.List;









import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.common.page.Pagination;

import com.hkr.core.dao.product.BrandDao;
import com.hkr.core.pojo.product.Brand;
import com.hkr.core.pojo.product.BrandQuery;

@Service("brandServiceImpl")
@Transactional
public class BrandServiceImpl implements BrandService{
	@Autowired
	private BrandDao brandDao;
	//品牌列表查询
	@Override
	public List<Brand> findBrandListByNameAndIsDisplay(String name,
			Integer isDisplay) {
		// TODO Auto-generated method stub
		
		//创建BrandQuery查询条件的对象
		BrandQuery brandQuery = new BrandQuery();
		if(name != null){
			brandQuery.setName(name);
		}
		if(isDisplay != null){
			brandQuery.setIsDisplay(isDisplay);
		}
		List<Brand> list = brandDao.findBrandByNameAndIsDisplay(brandQuery);
		
		return list;
	}
	//分页
	public Pagination findBrandPageByQuery(String name, Integer isDisplay,
			Integer pageNo) {
		StringBuilder params = new StringBuilder();
		
		BrandQuery brandQuery = new BrandQuery();
		brandQuery.setPageNo(Pagination.cpn(pageNo));
		brandQuery.setPageSize(7);
		if(name != null){
			brandQuery.setName(name);
			params.append("&name=").append(name);
		}
		if(isDisplay != null){
			brandQuery.setIsDisplay(isDisplay);
			params.append("&isDisplay").append(isDisplay);
		}
		Integer count = brandDao.findBrandPageByQueryCount(brandQuery);
		List<Brand> list = brandDao.findBrandPageByQuery(brandQuery);
		//c创建分页对象 
		Pagination pagination = new Pagination(
				brandQuery.getPageNo(),
				brandQuery.getPageSize(),
				count,
				list
				);
		//翻页
		String url="/brand/list.action";
		pagination.pageView(url, params.toString());
		
		return pagination;
	}
	
	//修改
	public Brand findBrandById(Long id) {
		Brand brand = brandDao.findBrandById(id);
		/*System.out.println("brandId="+brand.getId());*/
		return brand;
	}

	//提交
	public void updateBrand(Brand brand) {
		brandDao.updateBrand(brand);
		
	}
	//批量删除
	public void delBrandAll(Long[] ids) {
		brandDao.delBrandAll(ids);
		
	}

}
