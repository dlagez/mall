package com.roczhang.mall.dao;

import com.roczhang.mall.nosql.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 搜索系统中的商品管理自定义Dao
 */
public interface EsProductDao {

    // 获取EsProduct列表
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
