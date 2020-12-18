package com.hkr.core.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hkr.core.dao.product.ProductDao;
import com.hkr.core.pojo.product.Product;
//加载spring   junit4 运行环境
@RunWith(value=SpringJUnit4ClassRunner.class)
//加载Spring配置文件,进而加载Spring的运行环境
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class TextTbTest {
	@Autowired
	public ProductDao productDao;
	@Test
	public void TextFindPorduteDao() throws Exception{
		List<Product> example = productDao.selectByExample(null);
		System.out.println("==========="+example);
	}

}
