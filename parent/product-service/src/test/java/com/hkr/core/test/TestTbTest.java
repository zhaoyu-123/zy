package com.hkr.core.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hkr.core.dao.TbTestDao;
import com.hkr.core.pojo.TbTest;
import com.hkr.core.service.TbTestService;

//加载spring   junit4 运行环境
@RunWith(value=SpringJUnit4ClassRunner.class)
//加载Spring配置文件,进而加载Spring的运行环境
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class TestTbTest {
	//1.注入TbTestDao
	@Autowired
	private TbTestDao tbDao;
	//注入TbTestService
	@Autowired
	private TbTestService tbService;
	@Test
	public void testInsertTb() throws Exception{
		TbTest tb = new TbTest();
		tb.setName("刘帅哥");
		tb.setBirthday(new Date());
		
		tbDao.insertTbTest(tb);
	}
	//测试事务
	@Test
	public void testInsertTbService() throws Exception{
		TbTest tb = new TbTest();
		
		
		tbService.insertTbTest(tb);
	}
}
