package com.Alek0m0m.library.spring.web.mvc;

import com.Alek0m0m.library.jpa.BaseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;


@RestController
public abstract class BaseRESTController<T extends BaseEntity<ID>, ID extends Serializable> implements BaseRESTControllerInterface<T, ID> {

    private final BaseService<T, ID> service;

    @Autowired
    protected BaseRESTController(BaseService<T, ID> service) {
        this.service = service;
    }

    public BaseService<T, ID> getService() {
        return service;
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
