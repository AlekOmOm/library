package com.Alek0m0m.library.jpa;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public interface EntityToDTOMapper<DI, R extends BaseEntityDTO<T>, T extends BaseEntity> extends Function<T, R> {

    R toDTO(DI di);

    default T toEntity(R dto) {
        return dto.toEntity();
    }

    R toDTO(T entity);

    BaseEntityDTO<T> toDTOBase(DI di);

    BaseEntity toEntityBase(BaseEntityDTO<T> dto);

    BaseEntityDTO<T> toDTOBase(BaseEntity entity);

    // --------------------- Implementing Map Operations used in BaseService operations ---------------------
    default R map(T entity) {
        return toDTO(entity);
    }

    default R map(DI di) {
        return toDTO(di);
    }

    default T map(R dto) {
        return toEntity(dto);
    }

}