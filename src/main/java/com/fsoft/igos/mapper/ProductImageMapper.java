package com.fsoft.igos.mapper;

import com.fsoft.igos.dto.ProductImageDTO;
import com.fsoft.igos.entity.ProductImageEntity;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductImageMapper extends AbstractMapper<ProductImageEntity, ProductImageDTO> {
    ProductImageDTO toDTO(ProductImageEntity entity);

    ProductImageEntity toEntity(ProductImageDTO dto);

    List<ProductImageDTO> toDTOList(Collection<ProductImageEntity> entityList);

    List<ProductImageEntity> toEntityList(Collection<ProductImageDTO> dtoList);
}
