package com.Alek0m0m.library.spring.web.mvc.base;

import com.Alek0m0m.library.jpa.BaseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public interface BaseServiceInterface<T extends BaseEntity<ID>, ID extends Serializable> {

    BaseRepository<T, ID> getRepository();

    T save(T entity);
    List<T> findAll();
    T findById(ID id);

    void deleteById(ID id);
}