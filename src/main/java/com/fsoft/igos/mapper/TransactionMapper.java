package com.fsoft.igos.mapper;

import com.fsoft.igos.dto.TransactionDTO;
import com.fsoft.igos.entity.TransactionEntity;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends AbstractMapper<TransactionEntity, TransactionDTO> {
    TransactionDTO toDTO(TransactionEntity entity);

    TransactionEntity toEntity(TransactionDTO dto);

    List<TransactionDTO> toDTOList(Collection<TransactionEntity> entityList);

    List<TransactionEntity> toEntityList(Collection<TransactionDTO> dtoList);
}
