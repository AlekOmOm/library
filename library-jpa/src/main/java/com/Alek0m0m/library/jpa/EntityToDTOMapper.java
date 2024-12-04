package com.Alek0m0m.library.jpa;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public interface EntityToDTOMapper<DI, R extends BaseEntityDTO<T>, T extends BaseEntity> extends Function<T, R> {

    public default R convert(DI dtoInput) {
        if (dtoInput == null) {
            return null;
        }
        return map(dtoInput, null);
    }

    public default T convert(R dto) {
        if (dto == null) {
            return null;
        }
        return toEntity(dto);
    }

    public default R convert(T entity) {
        if (entity == null) {
            return null;
        }
        return map(null, entity);
    }




    // --------------------- Mapping ---------------------

    abstract R map(DI dtoInput, T entity);

    default R mapInputToDTO(DI di) {
        if (di == null) {
            return null;
        }
        return map(di, null);
    }

    default T toEntity(R dto) {
        if (dto == null) {
            return null;
        }
        return dto.toEntity();
    }

    default R mapEntityToDTO(T entity) {
        if (entity == null) {
            return null;
        }
        return map(null, entity);
    }

    default Collection<R> mapToDTOs(List<T> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream().map(this::mapEntityToDTO).toList();
    }

    default Collection<T> mapToEntities(List<R> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream().map(this::toEntity).toList();
    }

    default Collection<R> mapInputsToDTOs(List<DI> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream().map(this::mapInputToDTO).toList();
    }
}