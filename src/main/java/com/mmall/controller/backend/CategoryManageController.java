package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by yimingfan on 9/9/18
 */



@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICategoryService iCategoryService;


    @RequestMapping(value ="add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"User not login please login");
        }
        //check if admin
        if(iUserService.checkAdminRole(user).isSuccess()){
            //is admin, add function for process category
            return iCategoryService.addCategory(categoryName,parentId);
        }else{
            return ServerResponse.createByErrorMessage("not admin, please use admin account.");
        }
    }
    @RequestMapping(value = "set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpSession session, Integer categoryId, String categoryName){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"User not login please login");
        }
        //check if admin
        if(iUserService.checkAdminRole(user).isSuccess()){
            //is admin, updateCategoryName
            return iCategoryService.updateCategoryName(categoryName,categoryId);
        }else{
            return ServerResponse.createByErrorMessage("not admin, please use admin account.");
        }
    }

    //无递归 查询当前节点子节点
    @RequestMapping(value = "get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpSession session, @RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"User not login please login");
        }
        //check if admin
        if(iUserService.checkAdminRole(user).isSuccess()){
            //is admin, check children category, 不递归 保持平级
            return iCategoryService.checkChildrenParallelCategory(categoryId);
        }else{
            return ServerResponse.createByErrorMessage("not admin, please use admin account.");
        }
    }

    /**
     * 此方法包含深度查询子节点id的内容
     * 查询当前节点下所有子节点
     * @param session
     * @param categoryId
     * @return
     */
    //无递归 查询当前节点子节点
    @RequestMapping(value="get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryandDeepChildrenParallelCategory(HttpSession session, @RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "User not login please login");
        }
        //check if admin
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //查询当前节点id 和递归子节点id
            //0-》1000-》100000
            return iCategoryService.selectCategoryAndChildrenById(categoryId);


        } else {
            return ServerResponse.createByErrorMessage("not admin, please use admin account.");
        }
    }
}
