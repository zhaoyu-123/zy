package com.hkr.core.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application-context.xml"})
public class testSiampleJedis {
	@Autowired
	private Jedis jedis;
	@Test
	public void SiampleJedis(){
		Jedis jedis = new Jedis("192.168.200.128",6379);
		jedis.set("key2","2");
		System.out.println("=================="+jedis.get("key2"));
	}
	@Test
	public void SiampleJedis1(){
		//Jedis jedis = new Jedis("192.168.200.128",6379);
		jedis.set("key3","3");
		System.out.println("=================="+jedis.get("key3"));
	}
}
