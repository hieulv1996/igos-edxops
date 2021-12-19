package com.fsoft.igos.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "transactionDetails")
@Data
public class TransactionDetailEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "transactionId")
    private Long transactionId;

    @Column(name = "cartId")
    private Long cartId;

    @Column(name = "status")
    private Boolean status;

}
