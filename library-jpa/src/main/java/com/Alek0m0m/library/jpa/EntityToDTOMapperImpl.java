package com.Alek0m0m.library.jpa;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

@Service

public abstract class EntityToDTOMapperImpl<DTOInput, R extends BaseEntityDTO<T>, T extends BaseEntity>  implements EntityToDTOMapper<DTOInput, R, T> {

    // Main operations for Use

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

        // List mapping
    public Collection<R> map(List<T> entities) {
        return entities.stream()
                .map(this::map)
                .toList();
    }

    public Collection<R> mapToEntity(List<DTOInput> dtoInputs) {
        return dtoInputs.stream()
                .map(this::map)
                .toList();
    }

    public Collection<T> mapToEntity(Collection<R> dtos) {
        return dtos.stream()
                .map(this::map)
                .toList();
    }






    // ------------------- Abstract methods -------------------

    public abstract BiFunction<DTOInput, T, R> toDTO(DTOInput dtoInput, T entity);

    @Override
    public R toDTO(DTOInput dtoinput) {
        return (R) toDTO(dtoinput, null);
    }

    @Override
    public R toDTO(T entity) {
        return (R) toDTO(null, entity);
    }

    @Override
    public T toEntity(R dto) {
        return dto.toEntity();
    }

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



    private void debugPrint(String method, String message) {
        System.out.println();
        System.out.println("debug check for"+ method);
        System.out.println(" "+message);
        System.out.println();
    }

}
