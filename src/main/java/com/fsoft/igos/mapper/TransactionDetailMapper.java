package com.fsoft.igos.mapper;

import com.fsoft.igos.dto.TransactionDetailDTO;
import com.fsoft.igos.entity.TransactionDetailEntity;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionDetailMapper extends AbstractMapper<TransactionDetailEntity, TransactionDetailDTO> {
    TransactionDetailDTO toDTO(TransactionDetailEntity entity);

    TransactionDetailEntity toEntity(TransactionDetailDTO dto);

    List<TransactionDetailDTO> toDTOList(Collection<TransactionDetailEntity> entityList);

    List<TransactionDetailEntity> toEntityList(Collection<TransactionDetailDTO> dtoList);
}
