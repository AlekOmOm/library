package com.Alek0m0m.library.spring.web.mvc;

import com.Alek0m0m.library.jpa.BaseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
public interface BaseRESTControllerInterface<T extends BaseEntity<ID>, ID extends Serializable> {
    BaseService<T, ID> getService();

    // ------------------- CRUD -------------------


    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity);

    @GetMapping()
    public ResponseEntity<List<T>> getAll();

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(ID id);

    @PutMapping("/{id}")
    public ResponseEntity<T> update(ID id, T entity);

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(ID id);
}
