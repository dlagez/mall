package com.roczhang.mall.controller;

import com.roczhang.mall.common.api.CommonPage;
import com.roczhang.mall.common.api.CommonResult;
import com.roczhang.mall.nosql.elasticsearch.document.EsProduct;
import com.roczhang.mall.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "EsProductController", description = "搜索商品管理")
@RequestMapping("/esProduct")
public class EsProductController {

    @Autowired
    private EsProductService esproductService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.GET)
    public CommonResult<Integer> importAllList() {
        int count = esproductService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "根据id删除商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult<Object> delete(@PathVariable Long id) {
        esproductService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据ids批量删除商品")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    public CommonResult<Object> delete(@RequestParam("ids")List<Long> ids) {
        esproductService.delete(ids);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public CommonResult<EsProduct> create(@PathVariable Long id) {
        EsProduct esProduct = esproductService.create(id);
        if (esProduct != null) {
            return CommonResult.success(esProduct);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                          @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                          @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductPage = esproductService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }


}
