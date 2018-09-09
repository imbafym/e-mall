package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.vo.ProductDetailVo;

/**
 * Created by yimingfan on 9/9/18
 */
public interface IProductService {

    ServerResponse updateOrSaveProduct(Product product);

    ServerResponse<String> setSalesStatus(Integer productId, Integer status);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId)
}
