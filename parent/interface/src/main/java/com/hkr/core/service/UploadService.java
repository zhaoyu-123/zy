package com.hkr.core.service;

public interface UploadService {
	//文件上传
	public String uploadPic(byte[] pic, String fileName, long size) throws Exception;
}
