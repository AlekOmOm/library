package com.Alek0m0m.library.jpa;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@FunctionalInterface
@Service
public interface EntityToDTOMapper<T, R> extends Function<T, R> {
    R map(T entity);

    @Override
    default R apply(T t) {
        return map(t);
    }


}