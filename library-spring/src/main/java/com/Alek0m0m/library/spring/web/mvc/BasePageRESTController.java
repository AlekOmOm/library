package com.Alek0m0m.library.spring.web.mvc;

import com.Alek0m0m.library.jpa.BaseEntity;
import com.Alek0m0m.library.jpa.BaseEntityDTO;
import com.Alek0m0m.library.jpa.BasePageRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class BasePageRESTController<T extends BaseEntity, R extends BaseEntityDTO<T>, ServiceClass> extends BaseRESTController<T,R, ServiceClass> {

    public BasePageRESTController(BaseService<T, R, BaseRepository<T>> service) {
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
