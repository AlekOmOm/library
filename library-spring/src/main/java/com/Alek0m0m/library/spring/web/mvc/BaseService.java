package com.Alek0m0m.library.spring.web.mvc;


import com.Alek0m0m.library.jpa.BaseEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public abstract class BaseService<T extends BaseEntity<ID>, ID extends Serializable> implements BaseServiceInterface<T, ID> {

    private final BaseRepository<T, ID> repository;

    @Autowired
    protected BaseService(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    public BaseRepository<T, ID> getRepository() {
        return repository;
    }

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    public T find(T t) {
        if (t.getId() != null) {
            return findById(t.getId());
        } else if (t.getName() != null) {
            return findByName(t.getName());
        } else {
            throw new EntityNotFoundException("Entity not found");
        }
    }

    @Override
    public T findById(ID id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    public T findByName(String name) {
        return getRepository().findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }


    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }
}
