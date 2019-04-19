package com.cugb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cugb.dao.ProductMapper;
import com.cugb.dao.PurchaseRecordMapper;
import com.cugb.entity.Product;
import com.cugb.entity.PurchaseRecord;

@Service
//使用时间戳限制重入得乐观锁实现抢购商品
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private ProductMapper productMapper = null;
	@Autowired
	private PurchaseRecordMapper purchaseRecordMapper = null;
	/*  todo
	@Autowired
	private StringRedisTemplate stringRedisTemplate = null;
	String purchaseScript = 
			//先将产品编号保存到集合中
			"redis.call('sadd',KEY[1],ARGV[2]) \n"
			//购买列表
			+ "local productPurchaseList = KEY[2]..ARGV[2] \n"
			//用户编号
			+"local userId =ARGV[1] \n"
			//产品键
			+ "local product = 'product_'..ARGV[2] \n"
			//购买数量
			+ "local quantity = tonumber(ARGV[3]) \n"
			//当前库存
			+ "local stock = tonumber(redis.call('hget',product,'stock')) \n"
			//价格
			+ " local price = tonumber(redis.call('hget',product,'price')) \n"
			//购买时间
			+ "local purchase_date = ARGV[4] \n"
			//库存不足，返回0
			+ "if stock < quantity then return 0 end \n"
			//减库存
			+ "stock = stock - quantity \n"
			+ "redis.call('hset',product,'stock',tostring(stock)) \n"
			//计算价格
			+ "local sum = price * quantity \n"
			//合并购买记录数据
			+ "local purchaseRecord = userId..','..quantity..','"
			+ "..sum..','..price..','..purchase_date \n"
			//将购买记录保存到list里
			+ "redis.call('rpush',productPurchaseList,purchaseRecord) \n"
			//返回成功
			+ "return 1 \n";
	//Redis 购买记录集合前缀
	private static final String PURCHASE_PRODUCT_LIST = "purchase_list_";
	//抢购商品集合
	private static final String PRODUCT_SCHEDULE_SET = "product_schedule_set";
	// 32位SHA1编码，第一次执行的时候先让Redis进行缓存脚本返回
	private String sha1 = null;
	public boolean purchaseRedis(Long userId,Long productId,int quantity)
	{
		//购买时间
		Long purchaseDate = System.currentTimeMillis();
		Jedis jedis = null;
		try {
			//获取原始连接
			jedis = (Jedis) stringRedisTemplate.getConnectionFactory().getConnection().getNativeConnection();
			//如果没有加载过，则先将脚本加载到Redis服务器，让其返回sha1
			if(sha1 == null)
			{
				sha1 =  jedis.scriptLoad(purchaseScript);
			}
			//执行脚本，返回结果
			Object res = jedis.evalsha(sha1,2,PRODUCT_SCHEDULE_SET,PURCHASE_PRODUCT_LIST,userId+"",productId+"",quantity+"",purchaseDate+"");
			Long result = (Long) res;
			return result==1;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			//关闭jedis连接
			if(jedis != null && jedis.isConnected())
			{
				jedis.close();
			}
		}
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean dealRedisPurchase(List<PurchaseRecord> purchaseRecords)
	{
		for(PurchaseRecord purchaseRecord:purchaseRecords)
		{
			purchaseRecordMapper.purchaseRecord(purchaseRecord);
			productMapper.decreaseProduct(purchaseRecord.getId(), purchaseRecord.getQuantity());
		}
	}
	*/
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	//启动数据库事务机制，并将隔离级别设置为读写提交
	public boolean purchase(Long userId, Long productId, int quantity) {
		// TODO Auto-generated method stub
		//当前时间
		long start = System.currentTimeMillis();
		//循环尝试直至成功
		while(true)
		{
			//循环时间
			long end = System.currentTimeMillis();
			//如果循环时间大于100ms返回终止循环
			if(end - start >100) {
				return false;
			}
			//获取产品
			Product product = productMapper.getProduct(productId);
			//比较库存与购买数量
			if(product.getStock() < quantity)
			{
				//库存不足
				return false;
			}
			//获取当前版本号
			int version = product.getVersion();
			//扣减库存,同时将当前版本号发送给后台进行比较
			int result = productMapper.decreaseProduct(productId, quantity,version);
			//如果更新数据失效，说明数据在多线程中被其他线程修改，导致失败返回
			if(result == 0)
			{
				return false;
			}
			//初始化购买记录
			PurchaseRecord purchaseRecord = this.initPurchaseRecord(userId, product, quantity);
			//插入购买记录
			purchaseRecordMapper.purchaseRecord(purchaseRecord);
			return true;
		}	
	}
	private PurchaseRecord initPurchaseRecord(Long userId,Product product,int quantity)
	{
		PurchaseRecord purchaseRecord = new PurchaseRecord();
		purchaseRecord.setNote("购买日记，时间:"+System.currentTimeMillis());
		purchaseRecord.setPrice(product.getPrice());
		purchaseRecord.setProductId(product.getId());
		purchaseRecord.setQuantity(quantity);
		double sum = product.getPrice() * quantity;
		purchaseRecord.setSum(sum);
		purchaseRecord.setUserId(userId);
		return purchaseRecord;
	}
}
