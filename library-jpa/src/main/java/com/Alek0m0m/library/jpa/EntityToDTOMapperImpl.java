package com.Alek0m0m.library.jpa;

import org.springframework.stereotype.Service;

@Service

public abstract class EntityToDTOMapperImpl<dtoinput, R extends BaseEntityDTO<T>, T extends BaseEntity>  implements EntityToDTOMapper<dtoinput, R, T> {

    @Override
    public abstract R toDTO(dtoinput dtoinput);

    @Override
    public abstract T toEntity(R dto) ;

    @Override
    public abstract R entityToDTO(T entity);

    public BaseEntityDTO<T> toDTOBase(dtoinput dtoinput) {
        return toDTO(dtoinput);
    }

    public BaseEntity toEntityBase(BaseEntityDTO<T> dto) {
        return toEntity((R) dto);
    }

    public BaseEntityDTO<T> entityToDTOBase(BaseEntity entity) {
        return entityToDTO((T) entity);
    }


    @Override
    public R map(T t) {
        return EntityToDTOMapper.super.map(t);
    }

    @Override
    public R map(dtoinput dtoinput) {
        return EntityToDTOMapper.super.map(dtoinput);
    }

    @Override
    public T map(R dto) {
        return EntityToDTOMapper.super.map(dto);
    }

    @Override
    public R apply(T entity) {
        return entityToDTO(entity);
    }


    private void debugPrint(String method, String message) {
        System.out.println();
        System.out.println("debug check for"+ method);
        System.out.println(" "+message);
        System.out.println();
    }

}
