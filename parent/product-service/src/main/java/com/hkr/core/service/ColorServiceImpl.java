package com.hkr.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.hkr.core.dao.product.ColorDao;
import com.hkr.core.pojo.product.Color;
import com.hkr.core.pojo.product.ColorQuery;
import com.hkr.core.pojo.product.ColorQuery.Criteria;

@Controller("colorServiceImpl")
@Transactional
public class ColorServiceImpl implements ColorService{
	@Autowired
	private ColorDao colorDao;
	
	//取出颜色
	@Override
	public List<Color> findColorList() {
		ColorQuery colorQuery = new ColorQuery();
		Criteria createCriteria = colorQuery.createCriteria();
		 createCriteria.andParentIdNotEqualTo(0L);
		
		 List<Color> list = colorDao.selectByExample(colorQuery);
		 
		return list;
	}

}
