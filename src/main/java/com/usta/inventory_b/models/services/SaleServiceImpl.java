package com.usta.inventory_b.models.services;

import com.usta.inventory_b.entities.ProductEntity;
import com.usta.inventory_b.entities.SaleDetailEntity;
import com.usta.inventory_b.entities.SaleEntity;
import com.usta.inventory_b.models.daos.ProductDao;
import com.usta.inventory_b.models.daos.SaleDao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Service
public class SaleServiceImpl implements SaleService {

    private final SaleDao saleDao;
    private final ProductDao productDao;

    public SaleServiceImpl(SaleDao saleDao, ProductDao productDao) {
        this.saleDao = saleDao;
        this.productDao = productDao;
    }

    @Override
    public List<SaleEntity> listAll() {
        return saleDao.findAll();
    }

    @Override
    public SaleEntity findById(Long id) {
        return saleDao.findById(id).orElseThrow(() -> new RuntimeException("Sale not found"));
    }

    @Override
    public SaleEntity viewDetail(Long idVenta) {
        return saleDao.viewDetail(idVenta).orElseThrow(() -> new RuntimeException("Sale not found"));
    }

    @Override
    public SaleEntity registerSale(SaleEntity venta) {
        BigDecimal total = BigDecimal.ZERO;
        for (SaleDetailEntity d : venta.getDetalleVentas()){
            ProductEntity p = productDao.findById(d.getProducto().getIdProd()).orElseThrow(() -> new RuntimeException("Product not found"));

            if (p.getStock() < d.getCantidad()){
                throw  new RuntimeException("Insufficient stock");
            }

            d.setPrecioUnit(p.getPrecio());
            d.setSubtotal(p.getPrecio().multiply(BigDecimal.valueOf(d.getCantidad())));
            d.setVenta(venta);

            total = total.add(d.getSubtotal());
            p.setStock(p.getStock() - d.getCantidad());
            productDao.save(p);
        }

        venta.setTotal(total);
        venta.setFecha(LocalDate.now());
        return saleDao.save(venta);

    }
}
