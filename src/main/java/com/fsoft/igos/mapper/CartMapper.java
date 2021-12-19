package com.fsoft.igos.mapper;

import com.fsoft.igos.dto.CartDTO;
import com.fsoft.igos.entity.CartEntity;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper extends AbstractMapper<CartEntity, CartDTO> {
    CartDTO toDTO(CartEntity entity);

    List<CartDTO> toDTOList(Collection<CartEntity> entityList);

    CartEntity toEntity(CartDTO dto);

    List<CartEntity> toEntityList(Collection<CartDTO> dtoList);
}
