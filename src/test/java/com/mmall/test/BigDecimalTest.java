package com.mmall.test;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by yimingfan on 11/9/18
 */
public class BigDecimalTest {

    @Test
    public void test1(){
        //java 浮点型计算丢失精度
        System.out.println(0.05+0.01);
        System.out.println(1.0-0.42);
        System.out.println(4.015*100);
        System.out.println(123.3/100);

    }

    @Test
    public void test2() {
        //Big Decimal 演示
        BigDecimal b1 = new BigDecimal(0.05);
        BigDecimal b2 = new BigDecimal(0.01);
        System.out.println(b1.add(b2));


    }


    @Test
    public void test3() {
        //Big Decimal 演示 String 构造器 商业计算使用bigdecimal String 构造器
        BigDecimal b1 = new BigDecimal("0.05");
        BigDecimal b2 = new BigDecimal("0.01");
        System.out.println(b1.add(b2));
       }
    }
