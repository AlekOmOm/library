package com.Alek0m0m.library.jpa;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public interface EntityToDTOMapper<DI, R extends BaseEntityDTO<T>, T extends BaseEntity> extends Function<T, R> {

    public default R convert(DI dtoInput) {
        return map(dtoInput, null);
    }

    public default T convert(R dto) {
        return toEntity(dto);
    }

    public default R convert(T entity) {
        return map(null, entity);
    }




    // --------------------- Mapping ---------------------

    abstract R map(DI dtoInput, T entity);

    default R mapInputToDTO(DI di) {
        return map(di, null);
    }

    default T toEntity(R dto) {
        return dto.toEntity();
    }

    default R mapEntityToDTO(T entity) {
        return map(null, entity);
    }

    default Collection<R> mapToDTOs(List<T> entities) {
        return entities.stream().map(this::mapEntityToDTO).toList();
    }

    default Collection<T> mapToEntities(List<R> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }

    default Collection<R> mapInputsToDTOs(List<DI> dtos) {
        return dtos.stream().map(this::mapInputToDTO).toList();
    }
}