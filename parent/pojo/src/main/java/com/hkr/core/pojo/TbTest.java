package com.hkr.core.pojo;

import java.io.Serializable;
import java.util.Date;

public class TbTest implements Serializable{
	
	
	private static final long serialVersionUID = 5764522894774698318L;
	
	private Integer id;
	private String name;
	private Date birthday;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	

}
