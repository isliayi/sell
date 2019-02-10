package com.moon.sell.controller;

import com.moon.sell.dataobject.ProductCategory;
import com.moon.sell.dataobject.ProductInfo;
import com.moon.sell.dto.OrderDTO;
import com.moon.sell.exception.SellException;
import com.moon.sell.form.ProductForm;
import com.moon.sell.service.CategoryService;
import com.moon.sell.service.ProductService;
import com.moon.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 卖家端商品
 *
 * @author moonglade on 2019-01-21.
 * @version 1.0
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public SellerProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    /**
     * 商品列表
     *
     * @param page 当前页面
     * @param size 每页记录数
     * @param map  数据
     * @return 跳转视图并且添加数据
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "10") Integer size, Map<String, Object> map) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<ProductInfo> orderDTOPage = productService.findAll(pageRequest);
        map.put("ProductInfoPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);

    }


    /**
     * 商品上架
     *
     * @param productId 商品ID
     * @param map
     * @return
     */
    @GetMapping("/on_sale")
    public ModelAndView onSale(String productId, Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 商品下架
     *
     * @param productId 商品ID
     * @param map
     * @return
     */
    @GetMapping("/off_sale")
    public ModelAndView offSale(String productId, Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }
//        查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);

        return new ModelAndView("product/index", map);
    }

    /**
     * 保存/更新
     *
     * @param form          商品对象表单
     * @param bindingResult 表单绑定参数结果
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空，说明是新增
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId());
            } else {
                form.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/product/index");
        return new ModelAndView("common/success", map);
    }
}
