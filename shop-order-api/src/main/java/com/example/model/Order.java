package com.example.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "t_shop_order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;//订单ID

    //用户
    private Long uid; //用户ID
    private String username;

    //商品
    private Long pid;
    private String pname;
    private Double pprice;

    //购买数量
    private Integer number;


}
