package com.fsoft.igos.mapper;

import com.fsoft.igos.dto.CartDTO;
import com.fsoft.igos.entity.CartEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDTO toDTO(CartEntity entity);

    List<CartDTO> toDTOList(List<CartEntity> entityList);

    CartEntity toEntity(CartDTO dto);

    List<CartEntity> toEntityList(List<CartDTO> dtoList);
}
