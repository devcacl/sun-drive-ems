package com.usta.inventory_b.models.services;

import com.usta.inventory_b.entities.ProductEntity;
import com.usta.inventory_b.models.daos.ProductDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<ProductEntity> ListAll() {
        return  productDao.findAll();
    }

    @Override
    public ProductEntity findById(Long id) {
        return productDao.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public void save(ProductEntity product) {
        productDao.save(product);
    }

    @Override
    public void delete(Long id) {
        productDao.deleteById(id);
    }

    @Override
    public List<ProductEntity> searchByName(String nombre) {
        return productDao.findByNombreProdContainingIgnoreCase(nombre);
    }

    @Override
    public List<ProductEntity> filterProducts(String name, String categoria, String marca, Long idProd) {
        return productDao.filterProducts(name, categoria, marca, idProd);
    }


}
