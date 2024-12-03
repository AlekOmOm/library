package com.Alek0m0m.library.spring.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;
import com.Alek0m0m.library.jpa.*;

@RestController
@CrossOrigin(origins = "*")
public abstract class BaseRESTController<DTOInput, DTO extends BaseEntityDTO<Entity>, Entity extends BaseEntity, Mapper extends EntityToDTOMapperImpl<DTOInput, DTO, Entity>, ServiceClass extends BaseService<DTOInput, DTO, Entity, Mapper, RepositoryClass>, RepositoryClass extends BaseRepository<Entity>> implements BaseRESTControllerInterface<DTOInput, DTO, Entity, Mapper> {

    private final BaseService<DTOInput, DTO, Entity, Mapper, RepositoryClass> baseService;
    protected final ServiceClass service;
    protected final Mapper mapper;

    @Autowired
    protected BaseRESTController(ServiceClass service) {
        this.baseService = service;
        this.service = service;
        mapper = (Mapper) service.getDtoMapper();
    }

    public BaseService <DTOInput, DTO, Entity, Mapper, BaseRepository<Entity>> getService() {
        return (BaseService<DTOInput, DTO, Entity, Mapper, BaseRepository<Entity>>) baseService;
    }

    protected DTO convert(DTOInput dtoInput) {
        return service.getDtoMapper().convert(dtoInput);
    }

    // ------------------- CRUD -------------------

    @PostMapping
    public ResponseEntity<DTO> create(@RequestBody DTOInput dtoinput) {
        if (dtoinput == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(getService().save(convert(dtoinput)));
    }

    @GetMapping
    public ResponseEntity<List<DTO>> getAll() {

        return ResponseEntity.ok(getService().findAll());
    }

    /* example:
    @GetMapping("/filter")
    public ResponseEntity<List<T>> getFilteredEntities() {
        return getAllEntities(BaseEntity::condition);
    }
     */
      /* example:
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllActiveUsers() {
        // Filter only active users and transform into UserDTO
        return getAllAndConvert(user -> user.isActive() ? new UserDTO(user) : null);
    } */

    public ResponseEntity<List<DTO>> getAllFiltered(Predicate<DTO> filter) {
        return ResponseEntity.ok(getService().findAll(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> getById(@PathVariable("id") long id) {
        return ResponseEntity.ok(getService().findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTO> update(@PathVariable("id") long id, @RequestBody DTOInput dtoinput) {
        DTO existingEntity = getService().findById(id);
        if (existingEntity == null) {
            return ResponseEntity.notFound().build();
        }
        DTO updatedEntity = this.convert(dtoinput);
            updatedEntity.setId(id);

        return ResponseEntity.ok(this.getService().save(updatedEntity));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        if (getService().findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        getService().deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // delete all
    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAll() {
        getService().deleteAll();
        return ResponseEntity.noContent().build();
    }

    // ------------------- Helper methods -------------------


}
