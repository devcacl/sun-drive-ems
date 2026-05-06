package com.usta.inventory_b.models.services;

import com.usta.inventory_b.entities.SaleDetailEntity;
import com.usta.inventory_b.entities.SaleEntity;

import java.util.List;

public interface SaleService {

    public List<SaleEntity> listAll();
    public SaleEntity findById(Long id);
    public SaleEntity viewDetail(Long idVenta);
    public SaleEntity registerSale(SaleEntity venta);
}
