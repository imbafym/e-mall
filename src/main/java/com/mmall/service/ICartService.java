package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;
import org.springframework.stereotype.Service;

/**
 * Created by yimingfan on 11/9/18
 */

public interface ICartService {
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo>update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    ServerResponse<CartVo> list(Integer userId);


    ServerResponse<CartVo> selectOrUnselect(Integer userId, Integer checked, Integer productId);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
