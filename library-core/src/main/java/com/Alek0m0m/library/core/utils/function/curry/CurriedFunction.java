package com.Alek0m0m.library.core.utils.function.curry;

import java.util.function.Function;

@FunctionalInterface
public interface CurriedFunction<T, U, R> {
    Function<T, Function<U, R>> curry();
}

