package com.Alek0m0m.library.spring.web.mvc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface BaseRESTControllerInterface<T extends BaseEntity,R extends BaseEntityDTO<T>> {
    BaseService<T, R, BaseRepository<T>> getService();

    // ------------------- CRUD -------------------

    // @PostMapping()
    // public ResponseEntity<R> create(BaseEntityDTO<T> entityDTO);

    @GetMapping()
    public ResponseEntity<List<R>> getAll();

    @GetMapping("/{id}")
    public ResponseEntity<R> getById(long id);

    @PutMapping("/{id}")
    public ResponseEntity<R> update(long id, R entityDTO);

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(long id);
}
