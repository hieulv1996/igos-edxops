package com.fsoft.igos.mapper;

import com.fsoft.igos.dto.CategoryDTO;
import com.fsoft.igos.dto.ProductImageDTO;
import com.fsoft.igos.entity.CategoryEntity;
import com.fsoft.igos.entity.ProductImageEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
    ProductImageDTO toDTO(ProductImageEntity entity);

    ProductImageEntity toEntity(ProductImageDTO dto);

    List<ProductImageDTO> toDTOList(List<ProductImageEntity> entityList);

    List<ProductImageEntity> toEntityList(List<ProductImageDTO> dtoList);
}
