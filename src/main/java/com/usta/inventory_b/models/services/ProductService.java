package com.usta.inventory_b.models.services;

import com.usta.inventory_b.entities.ProductEntity;


import java.util.List;


public interface ProductService {
    public List<ProductEntity> ListAll();
    public ProductEntity findById(Long id);
    public void save(ProductEntity product);
    public void delete(Long id);
    public List<ProductEntity> searchByName(String nombre);

    List<ProductEntity> filterProducts(String name, String categoria, String marca, Long idProd);

}
