package com.roczhang.mall.service.impl;

import com.roczhang.mall.dao.EsProductDao;
import com.roczhang.mall.nosql.elasticsearch.document.EsProduct;
import com.roczhang.mall.nosql.elasticsearch.repository.EsProductRepository;
import com.roczhang.mall.service.EsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 商品搜索管理Service实现类
 */
@Service
public class EsProductServiceImpl implements EsProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class);

    // EsProduct的操作类
    @Autowired
    private EsProductDao productDao;

    // elasticsearch 操作类
    @Autowired
    private EsProductRepository productRepository;

    /**
     * 将数据从数据库查询出来，将数据放入es，并计算数据的数量
     * @return
     */
    @Override
    public int importAll() {
        List<EsProduct> esProductList = productDao.getAllEsProductList(null);
        // 将所有商品添加到es
        Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result ++;
            iterator.next();
        }
        return result;
    }

    /**
     * 根据id删除es里面的数据
     * @param id
     */
    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * 创建一个产品对象，是从数据库中查询出所有的产品列表，如果查询出来了结果，
     * 就将数据中查询出来的产品列表的第一个数据拿出来。存储到es中。并将这个数据返回。
     * @param id
     * @return
     */
    @Override
    public EsProduct create(Long id) {
        EsProduct result = null;
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        if (esProductList.size() > 0) {
            EsProduct esProduct = esProductList.get(0);
            result = productRepository.save(esProduct);
        }
        return result;
    }

    /**
     * 拿到id列表，根据id列表创建出只有id的产品列表，然后将es的这些id删除（根据产品列表）。
     * @param ids
     */
    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            productRepository.deleteAll(esProductList);
        }
    }

    /**
     * 传入关键字，在es中使用该关键字在es中搜索名字，下标题和关键字。
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }
}
