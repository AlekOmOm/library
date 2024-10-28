package com.Alek0m0m.library.spring.web.mvc;

import com.Alek0m0m.library.jpa.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class BaseRepositoryAbstract<T extends BaseEntity> implements BaseRepository<T> {

    // Utilizing the BaseRepositoryInterface
    // gives us access to the custom methods defined in the interface
    // and the default methods defined in JpaRepository
    // without having to implement them in the repository class
    private final BaseRepository<T> repository;

    @Autowired
    public BaseRepositoryAbstract(BaseRepository<T> repository) {
        this.repository = repository;
    }

    // can call JPA methods directly on the repository
    protected BaseRepository<T> getRepository() {
        return repository;
    }

}
