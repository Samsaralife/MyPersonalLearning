package com.cugb.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/file")
public class FileUploadController {

	@GetMapping("/upload")
	public String uploadPage()
	{
		return "upload";
	}
	
	//使用HttpServletRequest作为参数
	@PostMapping("/request")
	@ResponseBody
	public Map<String, Object> uploadRequest(HttpServletRequest request)
	{
		boolean flag = false;
		Map<String, Object> dealResultMap = new HashMap<>();
		MultipartHttpServletRequest mRequest = null;
		//强制转换为MultipartHttpservletRequest
		if(request instanceof MultipartHttpServletRequest)
		{
			mRequest = (MultipartHttpServletRequest) request;
		}else {
			dealResultMap.put("success", false);
			dealResultMap.put("message", "上传文件失败");
			return dealResultMap;
		}
		//获取MultpartFile 文件信息
		MultipartFile multipartFile = mRequest.getFile("file");
		//获取源文件名称
		String fileName = multipartFile.getOriginalFilename();
		System.out.println("源文件名称---->"+fileName);
		File file = new File(fileName);
		try {
			//保存文件
			multipartFile.transferTo(file);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			dealResultMap.put("success", false);
			dealResultMap.put("message", "上传时发生异常");
			return dealResultMap;
		}
		dealResultMap.put("success", true);
		dealResultMap.put("message", "上传成功");
		return dealResultMap;
	}
	//使用Spring Mvc 的MultipartFile类作为参数
	@PostMapping("/multipart")
	@ResponseBody
	public Map<String, Object> uploadMultipartFile(MultipartFile multipartFile)
	{
		Map<String, Object> dealResultMap = new HashMap<>();
		String fileName = multipartFile.getOriginalFilename();
		File file = new File(fileName);
		try {
			multipartFile.transferTo(file);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			dealResultMap.put("success", false);
			dealResultMap.put("message", "文件上传时发生异常");
			return dealResultMap;
		}
		dealResultMap.put("success", true);
		dealResultMap.put("message", "上传成功");
		return dealResultMap;
	}
	
}
