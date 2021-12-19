package com.fsoft.igos.mapper;

import com.fsoft.igos.dto.UserDTO;
import com.fsoft.igos.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(UserEntity entity);

    UserEntity toEntity(UserDTO dto);

    List<UserDTO> toDTOList(List<UserEntity> entityList);

    List<UserEntity> toEntityList(List<UserDTO> dtoList);
}
