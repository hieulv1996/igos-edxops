package com.fsoft.igos.mapper;

import com.fsoft.igos.dto.UserDTO;
import com.fsoft.igos.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends AbstractMapper<UserEntity, UserDTO> {
    UserDTO toDTO(UserEntity entity);

    UserEntity toEntity(UserDTO dto);

    List<UserDTO> toDTOList(Collection<UserEntity> entityList);

    List<UserEntity> toEntityList(Collection<UserDTO> dtoList);
}
