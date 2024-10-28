package com.Alek0m0m.library.spring.web.mvc;

import com.Alek0m0m.library.jpa.BaseEntity;
import com.Alek0m0m.library.jpa.BaseEntityDTO;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public interface BaseServiceInterface<T extends BaseEntity, R extends BaseEntityDTO<T>> {

    BaseRepository<T> getRepository();

    R save(BaseEntityDTO<T> entityDTO);


    List<R> findAll();
    R findById(long id);

    void deleteById(long id);
}