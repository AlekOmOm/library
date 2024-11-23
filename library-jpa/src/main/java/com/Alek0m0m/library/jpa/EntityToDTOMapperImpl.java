package com.Alek0m0m.library.jpa;

import org.springframework.stereotype.Service;

@Service

public abstract class EntityToDTOMapperImpl<DTOInput, R extends BaseEntityDTO<T>, T extends BaseEntity>  implements EntityToDTOMapper<DTOInput, R, T> {

    // ------------------- Abstract methods -------------------
    @Override
    public abstract R toDTO(DTOInput dtoinput);

    @Override
    public abstract T toEntity(R dto) ;

    @Override
    public abstract R toDTO(T entity);

    // ------------------- Base methods -------------------
    public BaseEntityDTO<T> toDTOBase(DTOInput dtoinput) {
        return toDTO(dtoinput);
    }

    public BaseEntity toEntityBase(BaseEntityDTO<T> dto) {
        return toEntity((R) dto);
    }

    public BaseEntityDTO<T> toDTOBase(BaseEntity entity) {
        return toDTO((T) entity);
    }


    @Override
    public R map(T t) {
        return EntityToDTOMapper.super.map(t);
    }

    @Override
    public R map(DTOInput dtoinput) {
        return EntityToDTOMapper.super.map(dtoinput);
    }

    @Override
    public T map(R dto) {
        return EntityToDTOMapper.super.map(dto);
    }

    @Override
    public R apply(T entity) {
        return toDTO(entity);
    }


    private void debugPrint(String method, String message) {
        System.out.println();
        System.out.println("debug check for"+ method);
        System.out.println(" "+message);
        System.out.println();
    }

}
