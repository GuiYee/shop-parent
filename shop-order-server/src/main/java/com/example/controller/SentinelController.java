package com.example.controller;

import com.example.service.Impl.GoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @Description  服务熔断降级   模拟高并发场景
 * @Author sunwei
 * @Date 2024/4/5 9:08
 **/
@RestController
public class SentinelController {
    @Autowired
    private GoodServiceImpl goodService;



    @RequestMapping("/sentinel1")
    public String Sentinel1() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "SentinelFirst";
    }

    @RequestMapping("/sentinel2")
    public String Sentinel2(){
        return "测试高并发下的问题";
    }

//    关联流控  -------  确保尽可能最大的资源给到写操作
//    写操作
    @RequestMapping("/sentinel-write")
    public String sentinel3(){
        return "sentiel-write";
    }


    //    读操作
    @RequestMapping("/sentinel-read")
    public String sentinel4(){
        return "sentiel-read";
    }

//    链路流控
    @RequestMapping("/queryGood")
    public String queryGood(){
        goodService.queryGood();
        return "查询订单";
    }

    @RequestMapping("/createGood")
    public String createGood(){
        goodService.queryGood();
        return "创建订单";
    }



}
