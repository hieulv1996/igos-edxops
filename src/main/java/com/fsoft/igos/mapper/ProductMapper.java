package com.fsoft.igos.mapper;

import com.fsoft.igos.dto.ProductDTO;
import com.fsoft.igos.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(ProductEntity entity);

    ProductEntity toEntity(ProductDTO dto);

    List<ProductDTO> toDTOList(List<ProductEntity> entityList);

    List<ProductEntity> toEntityList(List<ProductDTO> dtoList);
}
