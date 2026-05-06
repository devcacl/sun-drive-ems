package com.usta.inventory_b.models.services;

import com.usta.inventory_b.entities.SaleDetailEntity;

import java.util.List;

public interface SaleDetailService {

    public List<SaleDetailEntity> findBySale(Long idVenta);
    public List<SaleDetailEntity> findByProduct(Long idProd);


}
