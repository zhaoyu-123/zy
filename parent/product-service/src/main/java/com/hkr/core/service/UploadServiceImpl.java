package com.hkr.core.service;

import org.springframework.stereotype.Service;

import com.hkr.core.common.FastDFSUtils;

@Service("uploadServiceImpl")
public class UploadServiceImpl implements UploadService{

	@Override
	public String uploadPic(byte[] pic, String fileName, long size)
			throws Exception {
		String path = FastDFSUtils.uploadFile(pic, fileName, size);
		return path;
	}
	
}
