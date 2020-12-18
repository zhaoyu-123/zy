package com.hkr.core.common;

import org.springframework.core.convert.converter.Converter;

public class CustomTrimStrConverter implements Converter<String,String>{

	@Override
	public String convert(String source) {
		if(source !=null){
			source=source.trim();
			if("".equals(source)){
				return null;
			}
			return source;
		}
		return null;
	}

}
