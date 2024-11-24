package com.Alek0m0m.library.spring.web.mvc;


import com.Alek0m0m.library.jpa.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public abstract class BaseService<dtoinput, R extends BaseEntityDTO<T>, T extends BaseEntity, DtoMapper extends EntityToDTOMapperImpl<dtoinput, R, T>, RepositoryClass extends BaseRepository<T>> implements BaseServiceInterface<R,T> {

    private final RepositoryClass repository;
    private final DtoMapper mapper;
    private final BaseService subServiceClass;

    @Autowired
    protected BaseService(RepositoryClass repository, DtoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.subServiceClass = null;
    }
    @Autowired
    protected BaseService(BaseService subServiceClass, RepositoryClass repository, DtoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.subServiceClass = subServiceClass;
    }

    public EntityToDTOMapper<dtoinput, R, T> getDtoMapper() {
        return mapper;
    }
    public BaseService getSubServiceClass() {
        return subServiceClass;
    }
    public BaseRepository<T> getRepository() {
        return repository;
    }

    protected void resetAutoIncrement() { repository.resetAutoIncrement();}

    // --------------------- CRUD ---------------------
    @Transactional
    public R save(BaseEntityDTO<T> entityDTO) {
        resetAutoIncrement();

        R e = mapper.toDTO(
                getRepository()
                        .save(entityDTO.toEntity()));
        return e;
    }

    @Transactional
    public List<R> saveAll(List<BaseEntityDTO<T>> entityDTOs) {
        resetAutoIncrement();
        return getRepository()
                .saveAll(
                        entityDTOs.stream()
                                .map(BaseEntityDTO::toEntity)
                                .collect(Collectors.toList()))
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    @Transactional
    public R update(BaseEntityDTO<T> entityDTO) {
        resetAutoIncrement();
        return mapper.apply(
                getRepository()
                        .save(entityDTO.toEntity()));
    }

    @Override
    @Transactional
    public List<R> findAll() {
        return getRepository().findAll().stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<R> findAll(Predicate<R> filter) {
        return repository.findAll().stream()
                .map(mapper)
                .filter(filter)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public R findById(long id) {
        return getRepository().findById(id)
                .map(mapper)
                .orElseThrow(()
                        -> new EntityNotFoundException("Entity not found"));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        getRepository().deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        resetAutoIncrement();
        getRepository().deleteAll();
    }

    // ------------------- Pagination -------------------

    public Page<R> findAllPaginated(Pageable pageable) {
        return getRepository().findAll().stream()
                .map(mapper)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> PageableExecutionUtils.getPage(list, pageable, list::size)));
    }

    public Page<R> findAllPaginated(Pageable pageable, Predicate<R> filter) {
        return getRepository().findAll().stream()
                .map(mapper)
                .filter(filter)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> PageableExecutionUtils.getPage(list, pageable, list::size)));
    }

    public Page<R> findAllAndConvertToDTO(Pageable pageable) {
        return getRepository().findAll().stream()
                .map(mapper)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> PageableExecutionUtils.getPage(list, pageable, list::size)));
    }
    public Page<R> findAllAndConvertToDTO(Pageable pageable, Predicate<T> filter) {
        return getRepository().findAll().stream()
                .filter(filter)
                .map(mapper)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> PageableExecutionUtils.getPage(list, pageable, list::size)));
    }

    // ------------------- Helper methods -------------------

    private void debugPrint(String method, String message) {
        System.out.println();
        System.out.println("debug check for "+ method);
        System.out.println(" "+message);
        System.out.println();
    }

}