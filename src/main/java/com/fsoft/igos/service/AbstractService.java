package com.fsoft.igos.service;

import com.fsoft.igos.dto.AuditableDTO;
import com.fsoft.igos.dto.ResponseDTO;
import com.fsoft.igos.entity.AuditableEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AbstractService {

    <E extends AuditableEntity> ResponseDTO getList(String search, Pageable pageable, Class<E> cls);

    <E extends AuditableEntity> List getReportList(String search, Class<E> cls);

    <E extends AuditableEntity> ResponseDTO getById(Long id);

    <E extends AuditableEntity, D extends AuditableDTO> ResponseDTO create(D dto);

    <D extends AuditableDTO> ResponseDTO update(D dto);

    <E extends AuditableEntity> ResponseDTO delete(Long id);

    <E extends AuditableEntity> ResponseDTO deleteList(List<Long> ids);
}
