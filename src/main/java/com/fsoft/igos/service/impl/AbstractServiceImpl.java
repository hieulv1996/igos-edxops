package com.fsoft.igos.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsoft.igos.dto.AuditableDTO;
import com.fsoft.igos.dto.ResponseDTO;
import com.fsoft.igos.entity.AuditableEntity;
import com.fsoft.igos.mapper.AbstractMapper;
import com.fsoft.igos.service.AbstractService;
import com.fsoft.igos.utils.ValidateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.*;

public abstract class AbstractServiceImpl implements AbstractService {

    public abstract AbstractMapper getMapper();

    public abstract JpaRepository getRepository();

    public abstract Logger getLogger();

    public abstract List<String> getSearchList();

    @Override
    public <E extends AuditableEntity> ResponseDTO getList(String filter, Pageable pageable, Class<E> cls) {
        ObjectMapper mapper = new ObjectMapper();
        getLogger().info("getList: {}", filter);
        ResponseDTO r = new ResponseDTO();
        Example<E> example = null;
        try {
            E entity = cls.newInstance();
            if (StringUtils.isNotEmpty(filter)) {
                Map<String, String> map = mapper.readValue(filter, Map.class);
                if (map.size() > 0) {
                    PropertyAccessor accessor = PropertyAccessorFactory.forBeanPropertyAccess(entity);
                    ExampleMatcher matcher = ExampleMatcher.matchingAll();
                    for (Map.Entry e : map.entrySet()) {
                        if (getSearchList().indexOf(e.getKey()) > -1) {
                            accessor.setPropertyValue(String.valueOf(e.getKey()), e.getValue());
                            matcher = matcher.withMatcher(String.valueOf(e.getKey()),
                                    ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
                        }
                    }
                    example = Example.of(entity, matcher);
                }
            }
            Page<E> all = example != null ? getRepository().findAll(example, pageable)
                    : getRepository().findAll(pageable);
            r.setData(getMapper().toDTOList(all.getContent()));
            r.setTotal((int) all.getTotalElements());
        } catch (Exception ex) {
            getLogger().error("getList failed", ex);
            r.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return r;
    }

    @Override
    public <T extends AuditableEntity> ResponseDTO getById(Long id) {
        getLogger().info("getById: {}", id);
        ResponseDTO r = new ResponseDTO();
        try {
            r.setData(getMapper().toDTO((T) getRepository().getOne(id)));
        } catch (Exception ex) {
            getLogger().error("getById failed", ex);
            r.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return r;
    }

    @Override
    public <E extends AuditableEntity, D extends AuditableDTO> ResponseDTO create(D dto) {
        getLogger().info("create: {}", dto);
        ResponseDTO r = new ResponseDTO();
        try {
            r.setData(getMapper().toDTO((E) getRepository().save(getMapper().toEntity(dto))));
        } catch (Exception ex) {
            getLogger().error("getById failed", ex);
            r.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return r;
    }

    @Override
    public <D extends AuditableDTO> ResponseDTO update(D dto) {
        getLogger().info("start update: {}", dto);
        ResponseDTO r = new ResponseDTO();
        try {
            getRepository().save(getMapper().toEntity(dto));
            getLogger().info("update {} successfully", dto);
            r.setHttpStatus(HttpStatus.OK);
        } catch (Exception e) {
            getLogger().error("update failed", e);
            r.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        getLogger().info("end update");
        return r;
    }

    @Override
    public <E extends AuditableEntity> ResponseDTO delete(Long id) {
        getLogger().info("start delete: {}", id);
        ResponseDTO r = new ResponseDTO();
        try {
            Optional<E> entity = getRepository().findById(id);
            if (ValidateUtil.isNullOrEmpty(entity)) {
                getLogger().warn("data not match with id: {}", id);
                r.setHttpStatus(HttpStatus.NOT_FOUND);
            } else {
                getRepository().delete(entity.get());
                r.setHttpStatus(HttpStatus.OK);
            }
        } catch (Exception e) {
            getLogger().error("delete failed", e);
            r.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return r;
    }

    @Override
    public <E extends AuditableEntity> ResponseDTO deleteList(List<Long> ids) {
        ResponseDTO r = new ResponseDTO();
        try {
            List<E> entities = getRepository().findAllById(ids);
            if (ValidateUtil.isNullOrEmpty(entities)) {
                getLogger().warn("data to delete is null");
                r.setHttpStatus(HttpStatus.NOT_FOUND);
            } else {
                getRepository().deleteAll(entities);
                r.setHttpStatus(HttpStatus.OK);
            }
        } catch (Exception e) {
            getLogger().error("delete list failed", e);
            r.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return r;
    }
}
