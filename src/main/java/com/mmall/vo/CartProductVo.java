package com.mmall.vo;

import java.math.BigDecimal;

/**
 * Created by yimingfan on 11/9/18
 */
public class CartProductVo {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer quantity; //购物车中的商品数量
    private String productName ;
    private String productSubtile ;
    private String productMainIamge ;
    private BigDecimal productPrice;
    private Integer productStatus;
    private BigDecimal productTotalPrice;
    private  Integer productStock;
    private Integer productChecked;//商品是否勾选

    private String limitQuantity; //限制数量的一个返回结果

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSubtile() {
        return productSubtile;
    }

    public void setProductSubtile(String productSubtile) {
        this.productSubtile = productSubtile;
    }

    public String getProductMainIamge() {
        return productMainIamge;
    }

    public void setProductMainIamge(String productMainIamge) {
        this.productMainIamge = productMainIamge;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public BigDecimal getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(BigDecimal productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Integer getProductChecked() {
        return productChecked;
    }

    public void setProductChecked(Integer productChecked) {
        this.productChecked = productChecked;
    }

    public String getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(String limitQuantity) {
        this.limitQuantity = limitQuantity;
    }
}
