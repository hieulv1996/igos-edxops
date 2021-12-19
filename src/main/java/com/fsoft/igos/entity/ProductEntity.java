package com.fsoft.igos.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
public class ProductEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "productName")
    private String productName;

    @Column(name = "productPrice")
    private BigDecimal productPrice;

    @Column(name = "saleOff")
    private BigDecimal saleOff;

    @Column(name = "categoryId")
    private Long categoryId;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "status")
    private Boolean status;

}
