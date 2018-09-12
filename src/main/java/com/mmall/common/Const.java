package com.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;

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

    public interface Cart{
        int CHECKED = 1; //选中状态
        int UN_CKECKED = 0; //未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL";
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";
    }


    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }

    public enum ProductStatusEnum{

        ON_SALE(1,"ON_SALE");

        private String value;
        private int code;

        ProductStatusEnum( int code,String value) {
            this.value = value;
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }
}
