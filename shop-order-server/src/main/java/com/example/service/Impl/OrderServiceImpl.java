package com.example.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.dao.OrderDao;
import com.example.model.Order;
import com.example.model.Product;
import com.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Order create(Long productId, Long userId) {

        log.info("接收到{}号商品的下单请求，接下来查询商品信息",productId);
        //远程调用 商品服务 查询信息
        //调用商品信息 是通过url来进行的 查询的
        //使用了loadbalanced注解 做负载均衡
        // url中只需要指定服务他默认会采用轮询策略进行负载均衡
        //在真正发送请求之前，将服务名替换成具体的ip地址和端口信息
        String url = "http://product-service/product/get?pid="+productId;
        log.info("远程调用地址{}",url );

        //指定的地址发送http请求，通过返回来的数据解析成 product对象
        Product product = restTemplate.getForObject(url, Product.class);
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
