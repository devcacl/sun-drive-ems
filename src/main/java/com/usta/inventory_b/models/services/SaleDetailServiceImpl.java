package com.usta.inventory_b.models.services;

import com.usta.inventory_b.entities.SaleDetailEntity;
import com.usta.inventory_b.models.daos.SaleDetailDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SaleDetailServiceImpl implements SaleDetailService {
    public final SaleDetailDao saleDetailDao;

    public SaleDetailServiceImpl(SaleDetailDao saleDetailDao) {
        this.saleDetailDao = saleDetailDao;
    }

    @Override
    public List<SaleDetailEntity> findBySale(Long idVenta) {
        return saleDetailDao.findByVenta_IdVenta(idVenta);
    }

    @Override
    public List<SaleDetailEntity> findByProduct(Long idProd) {
        return saleDetailDao.findByProducto_IdProd(idProd);
    }
}
