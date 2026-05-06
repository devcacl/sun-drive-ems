package com.usta.inventory_b.models.daos;

import com.usta.inventory_b.entities.ProductEntity;
import com.usta.inventory_b.entities.SaleDetailEntity;
import com.usta.inventory_b.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleDetailDao extends JpaRepository<SaleDetailEntity, Long> {

    List<SaleDetailEntity> findByVenta_IdVenta(Long idVenta);
    List<SaleDetailEntity> findByProducto_IdProd(Long idProd);

}
