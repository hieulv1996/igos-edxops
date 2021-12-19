package com.fsoft.igos.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
public class CategoryEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "categoryName")
    private String categoryName;

    @Column(name = "categoryType")
    private String categoryType;

    @Column(name = "status")
    private boolean status;

}
