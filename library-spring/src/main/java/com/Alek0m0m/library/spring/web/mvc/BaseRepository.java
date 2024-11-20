package com.Alek0m0m.library.spring.web.mvc;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.Alek0m0m.library.jpa.*;
@Repository
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {


    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE #{#entityName} AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

}

