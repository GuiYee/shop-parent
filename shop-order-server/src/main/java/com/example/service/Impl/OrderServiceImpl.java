package com.example.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.dao.OrderDao;
import com.example.feign.ProductFeignApi;
import com.example.model.Order;
import com.example.model.Product;
import com.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductFeignApi productFeignApi;

    @Override
    public Order create(Long productId, Long userId) {

        log.info("接收到{}号商品的下单请求，接下来查询商品信息",productId);
        //远程调用 商品服务 查询信息
        Product product = productFeignApi.get(productId);
        log.info("查询到{}号商品信息，内容为：{}",productId, JSONObject.toJSONString(product));

        //创建订单并保存
        Order order = new Order();
        order.setUid(userId);
        order.setUsername("孙大炮");
        order.setPid(productId);
        order.setPname(product.getName());
        order.setPprice(product.getPrice());
        order.setNumber(1);
        orderDao.save(order);
        log.info("创建订单信息成功，内容为：{}",JSONObject.toJSONString(order));
        return order;
    }
}
