package com.hkr.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.Jedis;

import com.hkr.core.dao.product.ProductDao;
import com.hkr.core.dao.product.SkuDao;
import com.hkr.core.pojo.product.Product;
import com.hkr.core.pojo.product.ProductQuery;
import com.hkr.core.pojo.product.ProductQuery.Criteria;
import com.hkr.core.pojo.product.Sku;

import cn.itcast.common.page.Pagination;

@Service("productServiceImpl")
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private SkuDao skuDao;
	@Autowired
	private Jedis jedis;
	@Override
	public Pagination findProductPagination(String name, Long brandId,
			Boolean isShow, Integer pageNo) throws Exception {
		// TODO Auto-generated method stub
		
		StringBuilder params = new StringBuilder();
		
		ProductQuery productQuery = new ProductQuery();
		Criteria createCriteria = productQuery.createCriteria();
		productQuery.setPageNo(Pagination.cpn(pageNo));
		productQuery.setPageSize(7);
		if(name !=null){
			createCriteria.andNameLike("%"+name+"%");
			params.append("&name=").append(name);
		}
		if(brandId != null){
			createCriteria.andBrandIdEqualTo(brandId);
			params.append("&brandId=").append(brandId);
		}
		if(isShow != null){
			createCriteria.andIsShowEqualTo(isShow);
			params.append("&isShow=").append(isShow);
		}else{
			createCriteria.andIsShowEqualTo(true);
			params.append("&isShow=").append(1);
		}
		int countByExample = productDao.countByExample(productQuery);
		List<Product> selectByExample = productDao.selectByExample(productQuery);
		Pagination pagination = new Pagination(productQuery.getPageNo(), productQuery.getPageSize(), countByExample, selectByExample);
		
		String url="/product/list.action";
		pagination.pageView(url, params.toString());
		return pagination;
	}
	//商品管理_商品提交
		@Override
		public void insertProduct(Product product) {
			/*
			 * 添加商品
			 * 要想添加商品,需要先设置商品的状态
			 */
			Long pno = jedis.incr("pno");
			product.setId(pno);
			
			
			//刚添加的商品,设置为  没有删除  的状态
			product.setIsDel(true);
			product.setCreateTime(new Date());
			//默认为下架的状态
			product.setIsShow(false);
			productDao.insertSelective(product);
			/*
			 * 根据添加商品的不同的颜色,不同的尺码,来初始化库存
			 */
			//遍历颜色
			for (String  colorId : product.getColors().split(",")) {
				//遍历尺码
				for (String  size  : product.getSizes().split(",")) {
					Long productId = product.getId();
					Sku sku = new Sku();
					//颜色id
					sku.setColorId(Long.parseLong(colorId));
					//创建时间
					sku.setCreateTime(new Date());
					//运费   默认十元
					sku.setDeliveFee(10f);
					//市场价
					sku.setMarketPrice(0f);
					//售价
					sku.setPrice(0f);
					sku.setProductId(productId);
					//尺码
					sku.setSize(size);
					//库存
					sku.setStock(0);
					//购买限制
					sku.setUpperLimit(200);
					System.out.println(sku.toString());
					
					skuDao.insertSelective(sku);
				}
			}
		
	}
		//商品上架
		public void isShow(Long[] ids) {
			if(ids!=null&& ids.length>0){
				for (Long id: ids) {
					Product product = new Product();
					product.setId(id);
					product.setIsShow(true);
					productDao.updateByPrimaryKeySelective(product);
					
				}
			}
			
		}
	 

}
