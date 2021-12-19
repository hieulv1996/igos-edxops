package com.fsoft.igos.mapper;

import com.fsoft.igos.dto.CategoryDTO;
import com.fsoft.igos.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends AbstractMapper<CategoryEntity, CategoryDTO> {
    CategoryDTO toDTO(CategoryEntity entity);

    CategoryEntity toEntity(CategoryDTO dto);

    List<CategoryDTO> toDTOList(Collection<CategoryEntity> entityList);

    List<CategoryEntity> toEntityList(Collection<CategoryDTO> dtoList);
}
