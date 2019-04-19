package com.cugb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cugb.entity.PurchaseRecord;

@Mapper
public interface PurchaseRecordMapper {

	//购买产品接口
	public int purchaseRecord(PurchaseRecord purchaseRecord);
	//根据用户id获取消费订单
	public List<PurchaseRecord> getPurchaseRecordByUserId(int userId);
}
