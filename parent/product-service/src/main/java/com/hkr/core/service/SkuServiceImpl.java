package com.hkr.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hkr.core.dao.product.ColorDao;
import com.hkr.core.dao.product.SkuDao;
import com.hkr.core.pojo.product.Sku;
import com.hkr.core.pojo.product.SkuQuery;
import com.hkr.core.pojo.product.SkuQuery.Criteria;
@Service("skuServiceImpl")
@Transactional
public class SkuServiceImpl implements SkuService{
	@Autowired
	private SkuDao skuDao;
	@Autowired
	private ColorDao colorDao;

	@Override
	public List<Sku> findSkuListByProductId(Long priductId) {
		SkuQuery skuQuery = new SkuQuery();
		Criteria createCriteria = skuQuery.createCriteria();
		createCriteria.andProductIdEqualTo(priductId);
		List<Sku> selectByExample = skuDao.selectByExample(skuQuery);
		
		if (selectByExample != null&& selectByExample.size()>0) {
			for (Sku sku : selectByExample) {
				sku.setColor(colorDao.selectByPrimaryKey(sku.getColorId()));
				System.out.println(sku.getColor());
			}
			
		}
		
		return selectByExample;
	}

	@Override
	public void updateSku(Sku sku) {
		skuDao.updateByPrimaryKeySelective(sku);
		
	}

	

}
