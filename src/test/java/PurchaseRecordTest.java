import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cugb.Application;
import com.cugb.dao.PurchaseRecordMapper;
import com.cugb.entity.PurchaseRecord;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@MapperScan(basePackages= {"com.cugb.dao"})
public class PurchaseRecordTest {

	private final static Logger LOGGER = LoggerFactory.getLogger(PurchaseRecordTest.class);
	@Autowired
	private PurchaseRecordMapper purchaseRecordMapper;
	
	@Test
	public void test1()
	{
		PurchaseRecord purchaseRecord = new PurchaseRecord();
		purchaseRecord.setId(1L);
		purchaseRecord.setNote("test");
		purchaseRecord.setPrice(88.00);
		purchaseRecord.setQuantity(1);
		purchaseRecord.setSum(88);
		purchaseRecord.setUserId(1L);
		purchaseRecord.setProductId(1L);
		int effectNum = purchaseRecordMapper.purchaseRecord(purchaseRecord);
		assertEquals(effectNum, 1);
	}
	@Test
	public void getRecordByUserId()
	{
		List<PurchaseRecord> list = purchaseRecordMapper.getPurchaseRecordByUserId(1);
		Iterator<PurchaseRecord> iterator = list.iterator();
		while(iterator.hasNext())
		{
			LOGGER.info("message===>"+iterator.next().getSum());
		}
	}
}
