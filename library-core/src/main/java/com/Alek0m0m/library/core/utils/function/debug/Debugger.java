package com.Alek0m0m.library.core.utils.function.debug;

import java.util.List;
import java.util.function.BiFunction;

public class Debugger {




    public static final BiFunction<Iterable<Object>, Iterable<Object>, Boolean> listsPrint = (x, y) -> {
        System.out.println();
        System.out.println(x);
        System.out.println(y);
        System.out.println();
        return true;
    };


}
