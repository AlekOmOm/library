package com.Alek0m0m.library.spring.web.mvc;
import com.Alek0m0m.library.jpa.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.function.Predicate;

public abstract class BasePageRESTController<DTOInput, DTO extends BaseEntityDTO<Entity>, Entity extends BaseEntity, Mapper extends EntityToDTOMapperImpl<DTOInput, DTO, Entity>, ServiceClass extends BaseService<DTOInput, DTO, Entity, Mapper, RepositoryClass>, RepositoryClass extends BaseRepository<Entity>> implements BaseRESTControllerInterface<DTOInput, DTO, Entity, Mapper> {
    private final BaseService<DTOInput, DTO, Entity, Mapper, RepositoryClass> baseService;
    protected final ServiceClass service;
    protected final Mapper mapper;

    public BasePageRESTController(ServiceClass service) {
        this.baseService = service;
        this.service = service;
        mapper = (Mapper) service.getDtoMapper();
    }

    // ------------------- Pagination -------------------

    public ResponseEntity<Page<DTO>> getAllPaginated(BasePageRequestDTO dto) {
        return ResponseEntity.ok(getService().findAllPaginated(dto.getPageable(dto)));
    }

    public ResponseEntity<Page<DTO>> getAllFilteredPaginated(BasePageRequestDTO dto, Predicate<DTO> filter) {
        return ResponseEntity.ok(getService().findAllPaginated(dto.getPageable(dto), filter));
    }

    public ResponseEntity<Page<DTO>> getAllAndConvertPaginated(BasePageRequestDTO dto) {
        return ResponseEntity.ok(getService().findAllAndConvertToDTO(dto.getPageable(dto)));
    }

    public ResponseEntity<Page<DTO>> getAllFilteredAndConvertPaginated(BasePageRequestDTO dto, Predicate<Entity> filter) {
        return ResponseEntity.ok(getService().findAllAndConvertToDTO(dto.getPageable(dto), filter));
    }
}