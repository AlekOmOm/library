package com.Alek0m0m.library.jpa;

import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public interface EntityToDTOMapper<dtoinput, R extends BaseEntityDTO<T>, T extends BaseEntity> extends Function<T, R> {

    R toDTO(dtoinput dtoinput);

    default T toEntity(R dto) {
        return dto.toEntity();
    }

    R toDTO(T entity);

    BaseEntityDTO<T> toDTOBase(dtoinput dtoinput);

    BaseEntity toEntityBase(BaseEntityDTO<T> dto);

    BaseEntityDTO<T> toDTOBase(BaseEntity entity);

    // apply using all the converters

    default R map(T t) {
        return toDTO(t);
    }

    default R map(dtoinput dtoinput) {
        return toDTO(dtoinput);
    }

    default T map(R dto) {
        return toEntity(dto);
    }

}