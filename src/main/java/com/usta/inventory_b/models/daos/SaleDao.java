package com.usta.inventory_b.models.daos;

import com.usta.inventory_b.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SaleDao extends JpaRepository<SaleEntity, Long> {

    @Query("""
    SELECT v FROM SaleEntity v
    LEFT JOIN FETCH v.detalleVentas d
    LEFT JOIN FETCH d.producto
    WHERE v.idVenta=?1

""")
    Optional<SaleEntity> viewDetail(Long idVenta);

}


