package com.fsoft.igos.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "carts")
@Data
public class CartEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "identityId")
    private String identityId;

    @Column(name = "productId")
    private Long productId;

    @Column(name = "status")
    private Boolean status;

}
