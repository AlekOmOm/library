package com.Alek0m0m.library.core.utils.function.curry;

import java.util.function.Function;

public class CurriedFunctions {

    // CurriedFunctions for Spring MVC sprecifically for Controller <-> Service

    public static <T, U, R> CurriedFunction<T, U, R> curry(Function<T, Function<U, R>> function) {
        return () -> function;
    }





}
