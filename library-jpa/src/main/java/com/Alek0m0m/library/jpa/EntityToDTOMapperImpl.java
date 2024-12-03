package com.Alek0m0m.library.jpa;

public abstract class EntityToDTOMapperImpl<DI, R extends BaseEntityDTO<T>, T extends BaseEntity> implements EntityToDTOMapper<DI, R, T> {

    @Override
    public R apply(T entity) {
        return toDTO(entity);
    }

    @Override
    public R toDTO(DI dtoInput) {
        return toDTO(dtoInput, null);
    }

    @Override
    public R toDTO(T entity) {
        return toDTO(null, entity);
    }

    @Override
    public T toEntity(R dto) {
        return dto.toEntity();
    }

    public abstract R toDTO(DI dtoInput, T entity);
}