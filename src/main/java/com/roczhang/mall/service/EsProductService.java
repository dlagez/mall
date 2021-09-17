package com.roczhang.mall.service;

import com.roczhang.mall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 商品搜索管理service
 */
public interface EsProductService {
    /**
     * 从数据库导入所有商品到ES
     * @return
     */
    int importAll();

    /**
     * 根据id删除商品
     * @param id
     */
    void delete(Long id);


    /**
     * 根据id创建商品
     * @param id
     * @return
     */
    EsProduct create(Long id);

    /**
     * 根据关键字搜索名称或者副标题
     * @param ids
     */
    void delete(List<Long> ids);

    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
}
