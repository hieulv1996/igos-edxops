package com.fsoft.igos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartDTO extends AuditableDTO {
    private Long id;
    private String identityId;
    private Long productId;
    private Boolean status;
}
