package com.Alek0m0m.library.spring.web.mvc;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

// @AutoService(Processor.class) // This automatically creates META-INF/services/javax.annotation.processing.Processor
public class RelationAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // Processing logic
        return true;
    }
}

