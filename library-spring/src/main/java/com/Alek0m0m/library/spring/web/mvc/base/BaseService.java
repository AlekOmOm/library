package com.Alek0m0m.library.spring.web.mvc.base;


import com.Alek0m0m.library.jpa.BaseEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public abstract class BaseService<T extends BaseEntity<ID>, ID extends Serializable, RepositoryClass> implements BaseServiceInterface<T, ID> {

    private final BaseRepository<T, ID> baseRepository;
    protected final RepositoryClass repository;

    @Autowired
    protected BaseService(BaseRepository<T, ID> repository) {
        this.baseRepository = repository;
        this.repository = (RepositoryClass) repository;
    }

    public BaseRepository<T, ID> getRepository() {
        return baseRepository;
    }

    // ------------------- CRUD -------------------
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

    public T updateWithSetRelations(ID id, T newEntity) {
        T existingEntity = findById(id);
        setRelations(newEntity, existingEntity);
        return save(newEntity);
    }

//    public T updateWithMergeRelations(ID id, T newEntity) {
//        T existingEntity = findById(id);
//        mergeRelations(newEntity, existingEntity);
//        return save(newEntity);
//    }

    // Abstract method for handling relations (parent-child, one-to-many, many-to-many)
    protected void setRelations(T newEntity, T existingEntity) {
        // any set operations in class

    }

    protected abstract void mergeRelations(T entity, T existingEntity);

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }
}
