package com.usta.inventory_b.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="ventas")
public class SaleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_venta")
    private Long idVenta;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="fecha", nullable = false)
    private LocalDate fecha;

    @NotNull
    @PositiveOrZero
    @Digits(integer=12, fraction = 2)
    @Column(name="total", precision = 14, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name="estado_v", nullable = false)
    private boolean estadoV = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cedula", nullable = false)
    private UserEntity usuario;

    @OneToMany(mappedBy = "venta", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SaleDetailEntity> detalleVentas = new ArrayList<>();

    public SaleEntity(){}
}




