package com.Alek0m0m.library.spring.web.mvc;

import com.Alek0m0m.library.jpa.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseServiceInterface< R extends BaseEntityDTO<T>, T extends BaseEntity> {

    BaseRepository<T> getRepository();

    R save(BaseEntityDTO<T> entityDTO);

    List<R> findAll();
    R findById(long id);

    void deleteById(long id);
}