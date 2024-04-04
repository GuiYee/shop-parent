package org.example.controller;

import com.example.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //商品信息查询
    @RequestMapping("/get")
    public Product findByPid(Long pid) {
        log.info("{}号商品的信息查询",pid);
        Product byPid = productService.findByPid(pid);
        log.info("查询到商品信息为{}",byPid);
        return byPid;
    }


}
