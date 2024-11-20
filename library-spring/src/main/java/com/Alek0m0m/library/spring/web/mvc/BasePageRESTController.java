package com.Alek0m0m.library.spring.web.mvc;
import com.Alek0m0m.library.jpa.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.function.Predicate;

public abstract class BasePageRESTController<DTOInput, DTO extends BaseEntityDTO<Entity>, Entity extends BaseEntity, Mapper extends EntityToDTOMapperImpl<DTOInput, DTO, Entity>, ServiceClass extends BaseService<DTOInput, DTO, Entity, Mapper, RepositoryClass>, RepositoryClass extends BaseRepository<Entity>> implements BaseRESTControllerInterface<DTOInput, DTO, Entity, Mapper> {

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