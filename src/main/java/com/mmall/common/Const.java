package com.mmall.common;

/**
 * Created by yimingfan on 6/9/18
 */
public class Const {

    public static final String CURRENT_USER = "CURRENT_USER";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";

    //定义借口是为了方便分组
    public interface Role{
        int ROLE_CUSTOMER = 0; //normal user
        int ROLE_ADMIN = 1; //admin
    }
}
