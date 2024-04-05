package com.example.feign;

import com.example.model.Product;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @Description
 * @Author knshljj
 * @Date 2024/4/5 14:26
 **/

@Component
public class ProductFeignFallBack implements ProductFeignApi{
    @Override
    public Product get(Long pid) {
        System.out.println("远程服务不可用，降级方法");
        Product product = new Product();
        product.setId(-1L);
        product.setPrice(0.00);
        product.setName("服务不可用");
        return product;
    }
}
