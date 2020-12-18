package com.hkr.core.common;

import org.apache.commons.io.FilenameUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

public class FastDFSUtils {
	/**
	 * 
	 * @param pic
	 *            byte数组类型文件
	 * @param fileName
	 *            文件名称
	 * @param size
	 * 
	 *            文件大小
	 * @return
	 * @throws Exception
	 */
	// 1. 设置管理节点ip和端口
	// 2. 创建和管理节点的对象
	// 3. 获取管理节点连接
	// 4. 创建存储节点, 第一个参数, 传入管理节点连接, 第二个参数为null, 无用的
	// 5. 获取上传文件的扩展名称
	// 6. 创建文件的属性对象
	// 7. 上传并返回图片地址
	public static String uploadFile(byte[] pic, String fileName, long size) throws Exception{
		ClassPathResource classPathResource = new ClassPathResource("fdfs_client.conf");
		ClientGlobal.init(classPathResource.getClassLoader().getResource("fdfs_client.conf").getPath());
		
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer connection = trackerClient.getConnection();
		
		StorageClient1 storageClient1 = new StorageClient1(connection, null);
		String ext = FilenameUtils.getExtension(fileName);
		System.out.println(ext);
		NameValuePair[] meta_list = new NameValuePair[3];
		
		meta_list[0]= new NameValuePair("fileName",fileName);
		meta_list[1]= new NameValuePair("fileExt",ext);
		meta_list[2]= new NameValuePair("fileSize",String.valueOf(size));
		
		String upload_file1 = storageClient1.upload_file1(pic, ext, meta_list);
		return upload_file1;
	}

}
