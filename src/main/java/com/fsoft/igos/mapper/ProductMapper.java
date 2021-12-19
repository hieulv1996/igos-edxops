package com.fsoft.igos.mapper;

import com.fsoft.igos.dto.ProductDTO;
import com.fsoft.igos.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper extends AbstractMapper<ProductEntity, ProductDTO> {
    ProductDTO toDTO(ProductEntity entity);

    ProductEntity toEntity(ProductDTO dto);

    List<ProductDTO> toDTOList(Collection<ProductEntity> entityList);

    List<ProductEntity> toEntityList(Collection<ProductDTO> dtoList);
}
