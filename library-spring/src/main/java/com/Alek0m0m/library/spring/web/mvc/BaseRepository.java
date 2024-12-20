package com.Alek0m0m.library.spring.web.mvc;

import com.Alek0m0m.library.spring.web.mvc.repoCustomImpl.CustomBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Alek0m0m.library.jpa.*;

@Repository
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}