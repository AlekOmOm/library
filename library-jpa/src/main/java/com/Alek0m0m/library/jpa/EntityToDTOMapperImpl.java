package com.Alek0m0m.library.jpa;

public abstract class EntityToDTOMapperImpl<DI, R extends BaseEntityDTO<T>, T extends BaseEntity> implements EntityToDTOMapper<DI, R, T> {

    @Override
    public R apply(T entity) {
        return mapEntityToDTO(entity);
    }


    @Override
    public R mapInputToDTO(DI dtoInput) {
        return map(dtoInput, null);
    }

    @Override
    public R mapEntityToDTO(T entity) {
        return map(null, entity);
    }

    @Override
    public T toEntity(R dto) {
        return dto.toEntity();
    }

    public abstract R map(DI dtoInput, T entity);
}