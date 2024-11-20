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

    private DTO map(DTOInput dtoInput) {
        return service.getDtoMapper().map(dtoInput);
    }

    // ------------------- CRUD -------------------

    @PostMapping
    public ResponseEntity<DTO> create(@RequestBody DTOInput dtoinput) {
        if (dtoinput == null) {
            return ResponseEntity.badRequest().build();
        }
        System.out.println();
        System.out.println(" dtoinput: " + dtoinput);
        System.out.println();

        return ResponseEntity.ok(getService().save(map(dtoinput)));
    }

    @GetMapping
    public ResponseEntity<List<DTO>> getAll() {
        System.out.println();
        System.out.println("getAll called");
        System.out.println();
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
    public ResponseEntity<DTO> getById(@PathVariable long id) {
        return ResponseEntity.ok(getService().findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTO> update(@PathVariable long id, @RequestBody DTOInput dtoinput) {
        DTO existingEntity = getService().findById(id);
        if (existingEntity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getService().save(existingEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
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

    @PostMapping("/reset-auto-increment")
    public ResponseEntity<Void> resetAutoIncrement() {
        getService().resetAutoIncrement();
        return ResponseEntity.noContent().build();
    }
}
