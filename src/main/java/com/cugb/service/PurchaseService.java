package com.cugb.service;

public interface PurchaseService {
/**
 * 处理购买业务
 * @param userid 用户id
 * @param productId 产品id
 * @param quanlity 购买数量
 * @return
 */
	public boolean purchase(Long userid,Long productId,int quantity);
}
