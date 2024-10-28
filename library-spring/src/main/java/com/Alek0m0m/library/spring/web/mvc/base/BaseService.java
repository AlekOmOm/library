package com.Alek0m0m.library.spring.web.mvc.base;


import com.Alek0m0m.library.jpa.BaseEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    public List<T> findAll(Predicate<T> filter) {
        return baseRepository.findAll().stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    public <R> List<R> findAllAndConvertToDTO(Function<T, R> entityToDtoMapper) { // TODO: DTO Component in the future (class, mapper, BaseEntity tight coupling)
        return baseRepository.findAll().stream()
                .map(entityToDtoMapper)
                .collect(Collectors.toList());
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
