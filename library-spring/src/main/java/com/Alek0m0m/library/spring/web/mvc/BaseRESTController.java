package com.Alek0m0m.library.spring.web.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;

@RestController
public abstract class BaseRESTController<T extends BaseEntity, R extends BaseEntityDTO<T>, ServiceClass extends BaseService<T, R, RepositoryClass>, RepositoryClass extends BaseRepository<T>> implements BaseRESTControllerInterface<T, R> {

    private final BaseService<T, R, RepositoryClass> baseService;
    protected final ServiceClass service;

    @Autowired
    protected BaseRESTController(ServiceClass service) {
        this.baseService = service;
        this.service = service;
    }

    public BaseService<T, R, BaseRepository<T>> getService() {
        return (BaseService<T, R, BaseRepository<T>>) baseService;
    }


    // ------------------- CRUD -------------------




    @PostMapping
    public ResponseEntity<R> create(@RequestBody R entityDTO) {
        return ResponseEntity.ok(this.getService().save(entityDTO));
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
    public ResponseEntity<R> update(@PathVariable long id, @RequestBody R entityDTO) {
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
