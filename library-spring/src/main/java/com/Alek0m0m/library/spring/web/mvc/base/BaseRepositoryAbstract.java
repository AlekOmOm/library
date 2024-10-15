package com.Alek0m0m.library.spring.web.mvc.base;

import com.Alek0m0m.library.jpa.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class BaseRepositoryAbstract<T extends BaseEntity<ID>, ID extends Serializable> {

    // Utilizing the BaseRepositoryInterface
    // gives us access to the custom methods defined in the interface
    // and the default methods defined in JpaRepository
    // without having to implement them in the repository class
    private final BaseRepository<T, ID> repository;

    @Autowired
    public BaseRepositoryAbstract(BaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    // can call JPA methods directly on the repository
    protected JpaRepository<T, ID> getRepository() {
        return repository;
    }

    public Optional<T> findByName(String name) {
        // Custom implementation for finding by name
        return repository.findAll().stream()
                .filter(entity -> name.equals(entity.getName()))
                .findFirst();
    }

    public List<T> findAllSortedByName() {
        // Custom implementation for finding all entities sorted by name
        return repository.findAll().stream()
                .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                .toList();
    }
}
