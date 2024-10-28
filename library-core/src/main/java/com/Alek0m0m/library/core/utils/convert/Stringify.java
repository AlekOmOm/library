package com.Alek0m0m.library.core.utils.convert;

import java.util.ArrayList;
import java.util.List;

public class Stringify {


    public static List<String> stringifyList(List<?> objectList) {
        List<String> stringList = new ArrayList<>();
        for (Object obj : objectList) {
            stringList.add(obj.toString());
        }
        return stringList;
    }
}
