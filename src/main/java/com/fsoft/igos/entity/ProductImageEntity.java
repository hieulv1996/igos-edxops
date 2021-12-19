package com.fsoft.igos.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "productImages")
@Data
public class ProductImageEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "productId")
    private Long productId;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "status")
    private Boolean status;

}
