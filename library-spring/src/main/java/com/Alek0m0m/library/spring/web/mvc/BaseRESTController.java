package com.Alek0m0m.library.spring.web.mvc;

import com.Alek0m0m.library.jpa.BaseEntity;

import com.Alek0m0m.library.jpa.BaseEntityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;


@RestController
public abstract class BaseRESTController<T extends BaseEntity, R extends BaseEntityDTO<T>, ServiceClass> implements BaseRESTControllerInterface<T,R> {

    private final BaseService<T, R, BaseRepository<T>> baseService;
    protected final ServiceClass service;

    @Autowired
    protected BaseRESTController(BaseService<T, R, BaseRepository<T>> service) {
        this.baseService = service;
        this.service = (ServiceClass) service;
    }

    public BaseService<T,R, BaseRepository<T>> getService() {
        return baseService;
    }

    // ------------------- CRUD -------------------

    @PostMapping
    public ResponseEntity<R> create(@RequestBody BaseEntityDTO<T> entityDTO) {
        return ResponseEntity.ok(getService().save(entityDTO));
    }

    @GetMapping
    public ResponseEntity<List<R>> getAll() {
        return ResponseEntity.ok(getService().findAll());
    }

    /* example:
    @GetMapping("/filter")
    public ResponseEntity<List<T>> getFilteredEntities() {
        return getAllEntities(BaseEntity::condition);
    }
     */
    public ResponseEntity<List<R>> getAllFiltered(Predicate<R> filter) { // Predicate parameter

        return ResponseEntity.ok(getService().findAll(filter));
    }

    /* example:
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllActiveUsers() {
        // Filter only active users and transform into UserDTO
        return getAllAndConvert(user -> user.isActive() ? new UserDTO(user) : null);
    } */

    @GetMapping("/{id}")
    public ResponseEntity<R> getById(@PathVariable long id) {
        return ResponseEntity.ok(getService().findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<R> update(@PathVariable long id, @RequestBody BaseEntityDTO entity) {
        R existingEntity = getService().findById(id);
        existingEntity.setId(id);
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

}
