package com.moon.sell.controller;

import com.moon.sell.VO.ProductInfoVO;
import com.moon.sell.VO.ProductVO;
import com.moon.sell.VO.ResultVO;
import com.moon.sell.dataobject.ProductCategory;
import com.moon.sell.dataobject.ProductInfo;
import com.moon.sell.service.CategoryService;
import com.moon.sell.service.ProductService;
import com.moon.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author moonglade on 2018-12-30.
 * @version 1.0
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    @Autowired
    public BuyerProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    @Cacheable(cacheNames = "product",key = "123")
    public ResultVO list() {
//        1.查询所有的上架商品
        List<ProductInfo> productInfos = productService.findUpAll();

//        2.查询类目(一次性查询)
        //List<Integer> categoryTypeList=new ArrayList<>();
        //传统方法
//        for (ProductInfo productInfo:productInfos){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        //精简做法(java8做法，lambda)
        List<Integer> categoryTypeList = productInfos.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());

        //查找所以商品类别
        List<ProductCategory> productCategoryList = categoryService
                .findByCategoryTypeIn(categoryTypeList);

//        3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();

            BeanUtils.copyProperties(productCategory,productVO);

            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfos) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }


        return ResultVOUtil.success(productVOList);
    }

}
