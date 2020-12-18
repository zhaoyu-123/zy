package com.hkr.core.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.fastjson.JSONObject;
import com.hkr.core.common.Constants;
import com.hkr.core.common.FastDFSUtils;
import com.hkr.core.service.UploadService;

@Controller
@RequestMapping("/upload")
public class UploadController {
	@Autowired
	private UploadService uploadService;
	//单个文件上传
	@RequestMapping("/updatePic")
	public void updatePic(MultipartFile pic, HttpServletResponse response) throws Exception {
		/*//1.获取文件的完整名称
		String fileName = pic.getOriginalFilename();
		//2.随机生成字符串作为文件名称+源文件扩展名组成新的文件名称,防止文件重名
		String newFileName = UUID.randomUUID().toString().replaceAll("-", "")+"."+FilenameUtils.getExtension(fileName);
		//3.将文件保存到本地硬盘上
		pic.transferTo(new File("E:\\Download\\"+newFileName));
		//4.返回图片路径
		String url = "/pic/"+newFileName;*/
		
		String url = uploadService.uploadPic(pic.getBytes(),pic.getOriginalFilename(),pic.getSize());
		
		JSONObject jo = new JSONObject();
		jo.put("url", Constants.UPLOAD_SERVER+url);
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(jo.toString());
	}
	
	@RequestMapping("/uploadPics")
	@ResponseBody
	public List<String> uploadPics(@RequestParam MultipartFile[] pics)throws Exception{
		List<String> urlList = new ArrayList<String>();
		if(pics!=null&&pics.length>0){
			for (MultipartFile pic : pics) {
				String url = uploadService.uploadPic(pic.getBytes(),pic.getOriginalFilename(),pic.getSize());
				urlList.add(Constants.UPLOAD_SERVER+url);
			}
		}
		return urlList;
		
	}
	@RequestMapping("/uploadFck")
	@ResponseBody
	public void uploadFck(HttpServletRequest request,HttpServletResponse response)throws Exception{
		MultipartRequest multipartRequest=(MultipartRequest)request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		Set<Entry<String,MultipartFile>> entrySet = fileMap.entrySet();
		for (Entry<String, MultipartFile> entry : entrySet) {
			MultipartFile pic = entry.getValue();
			String url = uploadService.uploadPic(pic.getBytes(),pic.getOriginalFilename(),pic.getSize());
		
			JSONObject jo = new JSONObject();
			jo.put("url", Constants.UPLOAD_SERVER+url);
			jo.put("error", 0);
			
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(jo.toString());
			
		}
	}

}
