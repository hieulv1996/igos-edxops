package com.fsoft.igos.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
@Data
public class TransactionEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "totalCart")
    private BigDecimal totalCart;

    @Column(name = "status")
    private Boolean status;

}
