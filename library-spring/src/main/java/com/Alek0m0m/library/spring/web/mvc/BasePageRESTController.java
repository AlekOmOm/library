package com.Alek0m0m.library.spring.web.mvc;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.function.Predicate;

public abstract class BasePageRESTController<T extends BaseEntity, R extends BaseEntityDTO<T>, ServiceClass extends BaseService<T, R, RepositoryClass>, RepositoryClass extends BaseRepository<T>> extends BaseRESTController<T, R, ServiceClass, RepositoryClass> {

    public BasePageRESTController(ServiceClass service) {
        super(service);
    }

    // ------------------- Pagination -------------------

    public ResponseEntity<Page<R>> getAllPaginated(BasePageRequestDTO dto) {
        return ResponseEntity.ok(getService().findAllPaginated(dto.getPageable(dto)));
    }

    public ResponseEntity<Page<R>> getAllFilteredPaginated(BasePageRequestDTO dto, Predicate<R> filter) {
        return ResponseEntity.ok(getService().findAllPaginated(dto.getPageable(dto), filter));
    }

    public ResponseEntity<Page<R>> getAllAndConvertPaginated(BasePageRequestDTO dto) {
        return ResponseEntity.ok(getService().findAllAndConvertToDTO(dto.getPageable(dto)));
    }

    public ResponseEntity<Page<R>> getAllFilteredAndConvertPaginated(BasePageRequestDTO dto, Predicate<T> filter) {
        return ResponseEntity.ok(getService().findAllAndConvertToDTO(dto.getPageable(dto), filter));
    }
}