package com.Alek0m0m.library.spring.web.mvc;


import com.Alek0m0m.library.jpa.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
    Optional<T> findByName(String name);
}
