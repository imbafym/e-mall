package com.mmall.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mmall.common.ServerResponse;
import com.mmall.dao.ShippingMapper;
import com.mmall.pojo.Shipping;
import com.mmall.service.IShippingService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by yimingfan on 12/9/18
 */

@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService{

    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public ServerResponse add(Integer userId, Shipping shipping){
        shipping.setUserId(userId);
        //这里sql方面修改了参数 可以获得shipping自增加的id值
        int rowCount = shippingMapper.insert(shipping);
        if(rowCount>0){
            Map result = Maps.newHashMap();
            result.put("shippingId",shipping.getId());
            return ServerResponse.createBySuccess("shipping address is created successfully",result);
        }
        return ServerResponse.createByErrorMessage("failed to create new address");
    }

    @Override
    public ServerResponse<String> del(Integer userId, Integer shippingId){
        int resultCount = shippingMapper.deleteByShippingIdAndUserId(userId,shippingId);

        if(resultCount>0){
            return ServerResponse.createBySuccess("delete address successfully");
        }
        return ServerResponse.createByErrorMessage("delete address failed");

    }

    @Override
    public ServerResponse update(Integer userId, Shipping shipping){
        shipping.setUserId(userId);
        //这里sql方面修改了参数 可以获得shipping自增加的id值
        int rowCount = shippingMapper.updateByShipping(shipping);
        if(rowCount>0){
            Map result = Maps.newHashMap();
            result.put("shippingId",shipping.getId());
            return ServerResponse.createBySuccess("shipping address is update successfully");
        }
        return ServerResponse.createByErrorMessage("failed to update new address");
    }

    @Override
    public ServerResponse<Shipping> select(Integer userId, Integer shippingId){
       Shipping shipping = shippingMapper.selectByShippingIdAndUserId(userId,shippingId);

        if(shipping == null){
            return ServerResponse.createByErrorMessage("cannot find the address");
        }
        return ServerResponse.createBySuccess("find address successfully",shipping);

    }


    @Override
    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
