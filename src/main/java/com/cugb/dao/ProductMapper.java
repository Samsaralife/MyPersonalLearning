package com.cugb.dao;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cugb.entity.Product;

@Mapper
public interface ProductMapper {
	//插入商品
	public int insertProduct(Product product);
	//获取产品
	public Product getProduct(@Param("id")Long id);
	//扣减产品
	public int decreaseProduct(@Param("id")Long id, @Param("quantity")int quantity,@Param("version")int version);
	//批量插入产品
	public int batchInsertProduct(List<Product> products);
	//更新产品
	public int updateProduct(Product product);
	//删除产品
	public int deleteProduct(Long productId);
}
