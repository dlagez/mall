package com.roczhang.mall.service;

import com.roczhang.mall.mbg.model.PmsBrand;

import java.util.List;

/**
 * 品牌管理的service
 */
public interface PmsBrandService {

    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBreand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
