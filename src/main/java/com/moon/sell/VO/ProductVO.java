package com.moon.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moon.sell.dataobject.ProductInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 商品(包含类目)
 * @author moonglade on 2018-12-30.
 * @version 1.0
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 5241062154068363103L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
