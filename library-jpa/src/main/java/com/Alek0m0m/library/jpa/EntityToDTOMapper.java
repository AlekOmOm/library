package com.Alek0m0m.library.jpa;

import java.util.function.Function;

@FunctionalInterface
public interface EntityToDTOMapper<T, R> extends Function<T, R> {
    R map(T entity);

    @Override
    default R apply(T t) {
        return map(t);
    }


}