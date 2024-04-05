package com.example.feign;


import com.example.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
* 商品远程调用商品
* */

/*name 就是远程调用的服务名*/
@FeignClient(name = "product-service",path = "/product",fallback = ProductFeignFallBack.class)
public interface ProductFeignApi {

    //http://product-service/product/get?pid=1
    @RequestMapping("/get")
    Product get(@RequestParam("pid") Long pid);
}
