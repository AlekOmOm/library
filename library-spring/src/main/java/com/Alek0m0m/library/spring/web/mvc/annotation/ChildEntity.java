package com.Alek0m0m.library.spring.web.mvc.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChildEntity {
    boolean optional() default false;  // To specify if the child is optional
}

