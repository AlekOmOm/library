package com.Alek0m0m.library.core.utils.function.curry;

import java.util.function.Function;

@FunctionalInterface
public interface CurriedFunctionTwo<T, U, P, R> {
    Function<T, Function<U, Function<P, R>>> curry();
}

