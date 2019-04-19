package com.cugb.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cugb.entity.PurchaseRecord;
/* todo 
@Service
public class TestServiceImpl implements TaskService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate = null;
	@Autowired
	private PurchaseService purchaseService = null;
	private static final String PRODUCT_SCHEDULE_SET = "product_schedule_set";
	private static final String PURCHASE_PRODUCT_LIST = "product_list_";
	//每次取出1000条，避免一次取出消耗太多内存
	private static final int ONE_TIME_SIZE=1000;
	@Override
	//每天凌晨1点钟开支执行任务
	@Scheduled(cron="0 0 1 * * ?")
	public void purchaseTask() {
		// TODO Auto-generated method stub
		System.out.println("定时任务开始...........");
		Set<String> productIdList = stringRedisTemplate.opsForSet().members(PRODUCT_SCHEDULE_SET);
		List<PurchaseRecord> purchaseRecords = new ArrayList<>();
		for(String productIdStr:productIdList)
		{
			Long productId = Long.parseLong(productIdStr);
			String purchaseKey =  PURCHASE_PRODUCT_LIST+ productId;
			BoundListOperations<String, String> operations  = stringRedisTemplate.boundListOps(purchaseKey);
			//计算记录数
			long size = stringRedisTemplate.opsForList().size(purchaseKey);
			Long times = size % ONE_TIME_SIZE == 0 ? size/ONE_TIME_SIZE:size/ONE_TIME_SIZE+1;
			for(int i=0;i<times;i++)
			{
				//获取至多TIME_SIZE个抢红包信息
				List<String> list = null;
				if(i==0)
				{
					list = operations.range(i * ONE_TIME_SIZE, (i+1) * ONE_TIME_SIZE);
				}else {
					list = operations.range(i * ONE_TIME_SIZE + 1, (i+1) * ONE_TIME_SIZE);
				}
				for(String prStr:list) {
					PurchaseRecord purchaseRecord = this.createPurchaseRecord(productId,prStr);
					list.add(prStr);
				}
				try {
					//该方法采用新建事务的方式，不会导致全局事务回滚
					purchaseService.delRedisPurchase(productId,list);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				//清空列表位空，等待重新写入数据
				list.clear();
			}
			//删除购买列表
			stringRedisTemplate.delete(purchaseKey);
			//从商品集合中删除商品
			stringRedisTemplate.opsForSet().remove(PRODUCT_SCHEDULE_SET, productIdStr);
		}
		System.out.println("定时任务结束........");
	}
	private PurchaseRecord createPurchaseRecord(Long productId, String prStr) {
		// TODO Auto-generated method stub
		String [] arr = prStr.split(",");
		Long userId = Long.parseLong(arr[0]);
		int quantity = Integer.parseInt(arr[1]);
		double sum = Double.parseDouble(arr[2]);
		double price = Double.parseDouble(arr[3]);
		Long time = Long.parseLong(arr[4]);
		Timestamp timestamp = new Timestamp(time);
		PurchaseRecord pRecord = new PurchaseRecord();
		pRecord.setProductId(productId);
		pRecord.setPurchaseTime(timestamp);
		pRecord.setPrice(price);
		pRecord.setQuantity(quantity);
		pRecord.setSum(sum);
		pRecord.setUserId(userId);
		pRecord.setNote("购买日子：时间:"+timestamp.getTime());
		return pRecord;
	}

}*/
