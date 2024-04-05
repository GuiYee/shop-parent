package com.example.service.Impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @Description  链路流控
 * @Author sunwei
 * @Date 2024/4/5 13:49
 **/

@Service
public class GoodServiceImpl {
//   SentinelResource（客户端显示的名称 显示名称）
    @SentinelResource("queryGood")
    public void queryGood(){
        System.out.println("查询商品");
    }

}
