package com.fsoft.igos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO extends AuditableDTO {
    private Long id;
    private String productName;
    private BigDecimal productPrice;
    private BigDecimal saleOff;
    private Long categoryId;
    private String description;
    private Integer quantity;
    private Boolean status;
}
