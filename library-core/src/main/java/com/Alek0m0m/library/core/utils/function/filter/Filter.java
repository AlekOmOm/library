package com.Alek0m0m.library.core.utils.function.filter;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Filter {



    private static final BiFunction<List<String>, List<String>, List<String>> filter = (unfiltered, checklist) -> unfiltered.stream()
            .filter(checklist::contains)
            .toList();


}
