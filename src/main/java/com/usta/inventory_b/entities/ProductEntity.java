package com.usta.inventory_b.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="productos")
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prod")
    private Long idProd;

    @NotBlank
    @Size(max = 50)
    @Column(name ="nombre_prod", length = 50, nullable = false)
    private String nombreProd;

    @NotBlank
    @Size(max = 50)
    @Column(name ="marca", length = 50, nullable = false)
    private String marca;

    @NotBlank
    @Size(max = 20)
    @Column(name ="categoria", length = 20, nullable = false)
    private String categoria;

    @NotNull
    @PositiveOrZero
    @Digits(integer=8, fraction = 2)
    @Column(name="precio", precision = 10, scale = 2, nullable = false)
    private BigDecimal precio;

    @NotNull
    @Min(0)
    @Column(name="stock", nullable = false)
    private Integer stock;

    @NotBlank
    @Size(max = 20)
    @Column(name = "estado_prod", length = 20, nullable = false)
    private String estadoProd;

    @Size(max=200)
    @Column(name ="foto_url", length = 200)
    private String fotoUrl;

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<SaleDetailEntity> detalles = new ArrayList<>();

    public ProductEntity(){}
}
