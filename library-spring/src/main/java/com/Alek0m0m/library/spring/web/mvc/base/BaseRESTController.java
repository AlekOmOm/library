package com.Alek0m0m.library.spring.web.mvc.base;

import com.Alek0m0m.library.jpa.BaseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


@RestController
public abstract class BaseRESTController<T extends BaseEntity<ID>, ID extends Serializable, ServiceClass> implements BaseRESTControllerInterface<T, ID> {

    private final BaseService<T, ID, BaseRepository<T, ID>> baseService;
    protected final ServiceClass service;

    @Autowired
    protected BaseRESTController(BaseService<T, ID, BaseRepository<T, ID>> service) {
        this.baseService = service;
        this.service = (ServiceClass) service;
    }

    public BaseService<T, ID, BaseRepository<T, ID>> getService() {
        return baseService;
    }

    // ------------------- CRUD -------------------

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        return ResponseEntity.ok(getService().save(entity));
    }

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        return ResponseEntity.ok(getService().findAll());
    }

    /* example:
    @GetMapping("/filter")
    public ResponseEntity<List<T>> getFilteredEntities() {
        return getAllEntities(BaseEntity::condition);
    }
     */
    public ResponseEntity<List<T>> getAllFiltered(Predicate<T> filter) { // Predicate parameter

        return ResponseEntity.ok(getService().findAll(filter));
    }

    public <R> ResponseEntity<List<R>> getAllAndConvert(Function<T, R> entityToDtoMapper) { // Function parameter // TODO param will be removed when DTO component is implemented

        return ResponseEntity.ok(getService().findAllAndConvertToDTO(entityToDtoMapper)); //
    }
    /* example:
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllActiveUsers() {
        // Filter only active users and transform into UserDTO
        return getAllAndConvert(user -> user.isActive() ? new UserDTO(user) : null);
    } */

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        T entity = getService().findById(id);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
        T existingEntity = getService().findById(id);
        existingEntity.setId(id);
        return ResponseEntity.ok(getService().save(existingEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        if (getService().findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        getService().deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
