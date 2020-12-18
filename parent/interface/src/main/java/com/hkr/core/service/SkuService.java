package com.hkr.core.service;

import java.util.List;

import com.hkr.core.pojo.product.Sku;

public interface SkuService {
	//取库存列表
	public List<Sku> findSkuListByProductId(Long priductId);
	public void updateSku(Sku sku);
}
