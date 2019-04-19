package com.cugb.controller;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cugb.service.PurchaseService;
import com.cugb.utils.Result;

@Controller
public class PurchaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);
	@Autowired
	private PurchaseService purchaseService ;
	@PostMapping("/purchase")
	@ResponseBody
	public Result purchase(@Param("userId")Long userId,@Param("productId")Long productId, @Param("quantity")int quantity)
	{
		boolean success = purchaseService.purchase(userId, productId, quantity);
		String message = success?"抢购成功":"抢购失败";
		Result result = new Result(success, message);
		LOGGER.info("message=="+message);
		return result;
	}
	@GetMapping("/test")
	public String test()
	{
		return "test";
	}
}
