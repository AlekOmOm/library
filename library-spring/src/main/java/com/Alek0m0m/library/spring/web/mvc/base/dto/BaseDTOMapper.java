package com.Alek0m0m.library.spring.web.mvc.base.dto;

import com.Alek0m0m.library.jpa.BaseEntity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class BaseDTOMapper {

    // ------------------------------ Main Operations ------------------------------
        // entity -> DTO
        // DTO -> entity

    // Map entity to DTO
    public static <T extends BaseEntity<ID>, ID extends Serializable> Object toDTO(T entity, Class<?> dtoClass) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Object dto = instantiateDTO(dtoClass);

        // Loop through fields in the entity and map them to the DTO
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            // Skip fields that should not be included in the DTO
            if (shouldSkipField(field)) continue;

            // Set the same field in the DTO
            Field dtoField = getFieldInClass(dtoClass, field.getName());
            if (dtoField != null) {
                dtoField.setAccessible(true);
                dtoField.set(dto, field.get(entity)); // Set the field value
            }
        }

        return dto;
    }



    // Map DTO to entity
    public static <T extends BaseEntity<ID>, ID extends Serializable> T toEntity(Object dto, T entity) throws IllegalAccessException {
        Class<?> dtoClass = dto.getClass();

        // Loop through fields in the DTO and map them to the entity
        for (Field field : dtoClass.getDeclaredFields()) {
            field.setAccessible(true);

            Field entityField = getFieldInClass(entity.getClass(), field.getName());
            if (entityField != null) {
                entityField.setAccessible(true);
                entityField.set(entity, field.get(dto)); // Set the field value
            }
        }

        return entity;
    }

    // ------------------------------ Helper ------------------------------
    private static Object instantiateDTO(Class<?> dtoClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        try {
            return dtoClass.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodException("DTO class must have a no-args constructor");
        }
    }

    private static boolean shouldSkipField(Field field) {
        // Implement logic to skip specific fields (e.g., IDs, relations)
        return false;
    }

    private static Field getFieldInClass(Class<?> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

}

