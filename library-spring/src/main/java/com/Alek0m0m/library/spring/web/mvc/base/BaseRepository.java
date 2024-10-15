package com.Alek0m0m.library.spring.web.mvc.base;


import com.Alek0m0m.library.jpa.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface BaseRepository<T extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<T, ID> {
    Optional<T> findByName(String name);
}
