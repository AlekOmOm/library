package com.Alek0m0m.library.jpa;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public interface EntityToDTOMapper<DI, R extends BaseEntityDTO<T>, T extends BaseEntity> extends Function<T, R> {

    R toDTO(DI di);

    T toEntity(R dto);

    R toDTO(T entity);

    default Collection<R> mapToDTOs(List<T> entities) {
        return entities.stream().map(this::toDTO).toList();
    }

    default Collection<T> mapToEntities(List<R> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }
}