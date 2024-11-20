package com.Alek0m0m.library.spring.web.mvc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.Alek0m0m.library.jpa.*;


@RestController
public interface BaseRESTControllerInterface<dtoinput, R extends BaseEntityDTO<T>, T extends BaseEntity, DtoMapper extends EntityToDTOMapperImpl<dtoinput, R, T>> {
    BaseService<dtoinput,R,T, DtoMapper,BaseRepository<T>> getService();

    // ------------------- CRUD -------------------

    // @PostMapping()
    // public ResponseEntity<R> create(BaseEntityDTO<T> entityDTO);

    @GetMapping()
    public ResponseEntity<List<R>> getAll();

    @GetMapping("/{id}")
    public ResponseEntity<R> getById(long id);

//    @PutMapping("/{id}")
//    public ResponseEntity<R> update(long id, R entityDTO);

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(long id);
}

