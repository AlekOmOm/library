package com.Alek0m0m.library.spring.web.mvc.event;

import com.Alek0m0m.library.jpa.BaseEntity;
import com.Alek0m0m.library.spring.web.mvc.annotation.ChildEntity;
import com.Alek0m0m.library.spring.web.mvc.base.BaseRESTController;
import com.Alek0m0m.library.spring.web.mvc.base.BaseRepository;
import com.Alek0m0m.library.spring.web.mvc.base.BaseService;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class RelationOrchestrator {

    private static SystemClasses classes = new SystemClasses();

    private static List<Class<? extends Annotation>> annotations = new ArrayList<>(List.of(
            ChildEntity.class,
            OneToMany.class,
            OneToOne.class
    ));

    public <T extends BaseEntity<ID>, ID extends Serializable> void handleRelations(T newEntity, T existingEntity) throws IllegalAccessException {
        // Get all fields of the class
        Field[] fields = newEntity.getClass().getDeclaredFields();

        // Loop over each field and check for @ChildEntity annotation
        for (Field field : fields) {
            for(Class<? extends Annotation> annotation: annotations) {
                if (field.isAnnotationPresent(annotation)) {
                    AnnotationHandling.handleRelations(annotation, newEntity, existingEntity, field);
                }
            }
        }
    }



    static class AnnotationHandling {

        protected static <T extends BaseEntity<ID>, ID extends Serializable> void handleRelations(Class<? extends Annotation> annotation, T newEntity, T existingEntity, Field field) throws IllegalAccessException {
            field.setAccessible(true);

            if (annotation == ChildEntity.class) {
                handleChildEntityRelations(newEntity, existingEntity, field);
            } else if (annotation == OneToMany.class || annotation == OneToOne.class) {
                handleJpaRelations(field, newEntity, existingEntity);
            }
        }


        protected static <T extends BaseEntity<ID>, ID extends Serializable> void handleChildEntityRelations(T newEntity, T existingEntity, Field field) throws IllegalAccessException {

            ChildEntity annotation = field.getAnnotation(ChildEntity.class);

            // Optional child entity handling
            if (annotation.optional()) {
                if (field.get(newEntity) == null) {
                    field.set(newEntity, field.get(existingEntity));
                }
            } else {
                // Required child entity handling (e.g., replacing with new values or ensuring proper linkage)
                if (field.get(newEntity) == null) {
                    throw new IllegalStateException("Child entity " + field.getName() + " is required but was not provided.");
                }
            }
        }

        protected static <T extends BaseEntity<ID>, ID extends Serializable> void handleJpaRelations(Field field, T newEntity, T existingEntity) throws IllegalAccessException {
            // JPA @OneToMany or @OneToOne relationships handling logic
            if (List.class.isAssignableFrom(field.getType())) {
                // Example for @OneToMany relation (List)
                List<?> newList = (List<?>) field.get(newEntity);
                List<?> existingList = (List<?>) field.get(existingEntity);

                if (existingList != null && newList != null) {
                    field.set(newList, existingList); // replace, TODO: add merging optionality logic
                }

                field.set(newEntity, newList);
            } else if (BaseEntity.class.isAssignableFrom(field.getType())) {
                // Example for @OneToOne relation
                BaseEntity<?> newChild = (BaseEntity<?>) field.get(newEntity);
                BaseEntity<?> existingChild = (BaseEntity<?>) field.get(existingEntity);

                if (newChild == null) {
                    field.set(newEntity, existingChild); // Retain existing relationship
                }
            }
        }
    }

    public static class SystemClasses {
        private static List<Class<? extends BaseEntity>> models = new ArrayList<>();
        private static List<Class<? extends BaseRepository>> repositories = new ArrayList<>();
        private static List<Class<? extends BaseService>> services = new ArrayList<>();
        private static List<Class<? extends BaseRESTController>> controllers = new ArrayList<>();

        public static void addModel(Class<? extends BaseEntity> model) {
            models.add(model);
        }

        public static void deleteModel(Class<? extends BaseEntity> model) {
            models.remove(model);
        }

        public static Class<? extends BaseEntity> getModel(int index) {
            return models.get(index);
        }

        public static void setModel(int index, Class<? extends BaseEntity> model) {
            models.set(index, model);
        }

        public static void addRepository(Class<? extends BaseRepository> repository) {
            repositories.add(repository);
        }

        public static void deleteRepository(Class<? extends BaseRepository> repository) {
            repositories.remove(repository);
        }

        public static Class<? extends BaseRepository> getRepository(int index) {
            return repositories.get(index);
        }

        public static void setRepository(int index, Class<? extends BaseRepository> repository) {
            repositories.set(index, repository);
        }

        public static void addService(Class<? extends BaseService> service) {
            services.add(service);
        }

        public static void deleteService(Class<? extends BaseService> service) {
            services.remove(service);
        }

        public static Class<? extends BaseService> getService(int index) {
            return services.get(index);
        }

        // getService of Type ?
        public static BaseEntity getService(BaseEntity entity) {
            return entity; // TODO
        }

        public static void setService(int index, Class<? extends BaseService> service) {
            services.set(index, service);
        }

        public static void addController(Class<? extends BaseRESTController> controller) {
            controllers.add(controller);
        }

        public static void deleteController(Class<? extends BaseRESTController> controller) {
            controllers.remove(controller);
        }

        public static Class<? extends BaseRESTController> getController(int index) {
            return controllers.get(index);
        }

        public static void setController(int index, Class<? extends BaseRESTController> controller) {
            controllers.set(index, controller);
        }
    }
}
