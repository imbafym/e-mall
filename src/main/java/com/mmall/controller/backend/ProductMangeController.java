package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Product;
import com.mmall.pojo.User;
import com.mmall.service.IProductService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by yimingfan on 9/9/18
 */

@Controller
@RequestMapping("/manage/product")
public class ProductMangeController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IProductService iProductService;


    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productSave(HttpSession session, Product product) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "User not login, plean login as admin");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //业务逻辑
            return  iProductService.updateOrSaveProduct(product);
        } else {
            return ServerResponse.createByErrorMessage("No admin privillage");
        }
    }


    @RequestMapping("set_save_status.do")
    @ResponseBody
    public ServerResponse setSaveStatus(HttpSession session, Integer prodcutId, Integer productStatus) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "User not login, plean login as admin");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //业务逻辑
            return  iProductService.setSalesStatus(prodcutId,productStatus);
        } else {
            return ServerResponse.createByErrorMessage("No admin privillage");
        }
    }


    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getDetail(HttpSession session, Integer prodcutId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "User not login, plean login as admin");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //业务逻辑
            return  iProductService.manageProductDetail(prodcutId);
        } else {
            return ServerResponse.createByErrorMessage("No admin privillage");
        }
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpSession session, Integer prodcutId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "User not login, plean login as admin");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //业务逻辑
            return  iProductService.
        } else {
            return ServerResponse.createByErrorMessage("No admin privillage");
        }
    }
}
