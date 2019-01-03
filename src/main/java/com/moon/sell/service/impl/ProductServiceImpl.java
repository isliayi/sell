package com.moon.sell.service.impl;

import com.moon.sell.dataobject.ProductInfo;
import com.moon.sell.dto.CartDTO;
import com.moon.sell.enums.ProductStatusEnum;
import com.moon.sell.enums.ResultEnum;
import com.moon.sell.exception.SellException;
import com.moon.sell.repository.ProductInfoRepository;
import com.moon.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author moonglade on 2018-12-25.
 * @version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductInfoRepository repository;

    @Autowired
    public ProductServiceImpl(ProductInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo=repository.findById(cartDTO.getProductId()).get();
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            repository.save(productInfo);
        }

    }
}
