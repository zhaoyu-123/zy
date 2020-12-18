package com.hkr.core.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hkr.core.dao.TbTestDao;
import com.hkr.core.pojo.TbTest;

@Service("tbTestServiceImpl")
@Transactional
public class TbTestServiceImpl implements TbTestService{
	//注入TbTestDao
	@Autowired
	private TbTestDao tbDao;
	
	@Override
	public void insertTbTest(TbTest tb) {
//		tb.setName("王八");
//		tb.setBirthday(new Date());
		tbDao.insertTbTest(tb);
		
//		tb.setName("王九");
//		tb.setBirthday(new Date());
//		tbDao.insertTbTest(tb);
//		
//		throw new RuntimeException();
	}

}
