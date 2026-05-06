package com.usta.inventory_b.models.daos;

import com.usta.inventory_b.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao extends JpaRepository<ProductEntity,Long> {
    List<ProductEntity> findByNombreProdContainingIgnoreCase(String nombreProd);
    List<ProductEntity> findAllByOrderByPrecioAsc();

    @Query("""
        SELECT p FROM ProductEntity p
        WHERE(:nombreProd IS NULL OR :nombreProd='' OR 
        LOWER(p.nombreProd) LIKE LOWER(CONCAT('%', :nombreProd, '%')))
        AND (:categoria IS NULL OR :categoria='' OR 
        LOWER(p.categoria) LIKE LOWER(CONCAT('%', :categoria, '%')))
        AND (:marca IS NULL OR :marca='' OR 
        LOWER(p.marca) LIKE LOWER(CONCAT('%', :marca, '%')))
        AND (:idProd IS NULL OR p.idProd = :idProd)
""")
    List<ProductEntity> filterProducts(
            @Param("nombreProd") String nombreProd,
            @Param("categoria") String categoria,
            @Param("marca") String marca,
            @Param("idProd") Long idProd

    );
}
