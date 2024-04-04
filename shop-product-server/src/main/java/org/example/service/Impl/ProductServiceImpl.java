package org.example.service.Impl;

import com.example.model.Product;
import org.example.Dao.ProductDao;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findByPid(Long pid) {
        return productDao.findById(pid).get();
    }
}
