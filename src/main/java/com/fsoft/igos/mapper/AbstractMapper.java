package com.fsoft.igos.mapper;

import com.fsoft.igos.dto.AuditableDTO;
import com.fsoft.igos.entity.AuditableEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;
import java.util.List;

public interface AbstractMapper<S extends AuditableEntity, T extends AuditableDTO> {
    @Simple
    T toDTO(S entity);

    @IterableMapping(qualifiedBy = Simple.class)
    List<T> toDTOList(Collection<S> entities);

    @Simple
    S toEntity(T dto);

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    @interface Simple {
    }
}
