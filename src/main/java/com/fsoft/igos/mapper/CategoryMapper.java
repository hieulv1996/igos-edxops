package com.fsoft.igos.mapper;

import com.fsoft.igos.dto.CategoryDTO;
import com.fsoft.igos.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(CategoryEntity entity);

    CategoryEntity toEntity(CategoryDTO dto);

    List<CategoryDTO> toDTOList(List<CategoryEntity> entityList);

    List<CategoryEntity> toEntityList(List<CategoryDTO> dtoList);
}
