package com.Alek0m0m.library.spring.web.mvc.annotation;
import com.Alek0m0m.library.spring.web.mvc.annotation.GenerateDTO;
import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

@AutoService(Processor.class)
public class RelationAnnotationProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(GenerateDTO.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // Get the Filer instance from the processing environment
        Filer filer = processingEnv.getFiler();

        // Iterate over the annotated elements
        for (Element element : roundEnv.getElementsAnnotatedWith(GenerateDTO.class)) {
            if (element instanceof TypeElement typeElement) {
                String className = typeElement.getSimpleName() + "DTO";
                String packageName = processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString();

                try {
                    // Use Filer to create a new source file for the DTO
                    JavaFileObject fileObject = filer.createSourceFile(packageName + "." + className);
                    try (PrintWriter out = new PrintWriter(fileObject.openWriter())) {
                        out.println("package " + packageName + ";");
                        out.println();
                        out.println("public class " + className + " {");

                        // For each field in the original class, generate a corresponding DTO field
                        for (Element enclosedElement : typeElement.getEnclosedElements()) {
                            if (enclosedElement.getKind().isField()) {
                                String fieldName = enclosedElement.getSimpleName().toString();
                                String fieldType = enclosedElement.asType().toString();
                                out.println("    private " + fieldType + " " + fieldName + ";");
                            }
                        }

                        out.println();
                        out.println("    // Getters and Setters");
                        for (Element enclosedElement : typeElement.getEnclosedElements()) {
                            if (enclosedElement.getKind().isField()) {
                                String fieldName = enclosedElement.getSimpleName().toString();
                                String fieldType = enclosedElement.asType().toString();
                                String capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                                // Getter
                                out.println("    public " + fieldType + " get" + capitalizedFieldName + "() {");
                                out.println("        return " + fieldName + ";");
                                out.println("    }");

                                // Setter
                                out.println("    public void set" + capitalizedFieldName + "(" + fieldType + " " + fieldName + ") {");
                                out.println("        this." + fieldName + " = " + fieldName + ";");
                                out.println("    }");
                            }
                        }

                        out.println("}");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true; // Indicate that the annotations have been processed
    }
}

