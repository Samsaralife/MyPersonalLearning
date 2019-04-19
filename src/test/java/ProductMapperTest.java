import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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
import com.cugb.dao.ProductMapper;
import com.cugb.entity.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@MapperScan(basePackages= {"com.cugb.dao"})
public class ProductMapperTest {

	private static final Long PRODUCTID=2L;
	private static final int STOCK = 10;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductMapperTest.class);
	@Autowired
	private ProductMapper productMapper;
	@Test
	public void test1()
	{
		Product product = productMapper.getProduct(PRODUCTID);
		//product.setId(1L);
		LOGGER.info("ProductId======"+ product.getId());
		assertEquals(STOCK, product.getStock());
	}
	
	@Test
	public void test2()
	{
		int effectNum = productMapper.decreaseProduct(PRODUCTID, 3,1);
		assertEquals(effectNum, 1);
	}
	
	@Test
	public void batchInsertProductTest() 
	{
		Product product = new Product();
		product.setId(3L);
		product.setStock(10);
		product.setProductName("OPPO R20");
		product.setVersion(1);
		product.setNote("OPPO R20，照亮你的美");
		product.setPrice(2499.00);
		
		Product product2 = new Product();
		product2.setId(4L);
		product2.setStock(10);
		product2.setProductName("SUMSAM Galary");
		product2.setVersion(1);
		product2.setNote("智能，只为创造幸福生活");
		product2.setPrice(4345.00);
		List<Product> products = new ArrayList<>();
		products.add(product);
		products.add(product2);
		int effectNum = productMapper.batchInsertProduct(products);
		assertEquals(effectNum, 2);
	}
	
}
