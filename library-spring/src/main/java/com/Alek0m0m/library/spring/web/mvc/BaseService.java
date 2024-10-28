package com.Alek0m0m.library.spring.web.mvc;


import com.Alek0m0m.library.jpa.BaseEntity;
import com.Alek0m0m.library.jpa.BaseEntityDTO;
import com.Alek0m0m.library.jpa.EntityToDTOMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public abstract class BaseService<T extends BaseEntity, R extends BaseEntityDTO<T>, RepositoryClass> implements BaseServiceInterface<T,R> {

    private final BaseRepository<T> baseRepository;
    protected final RepositoryClass repository;
    private final EntityToDTOMapper<T, R> entityToDtoMapper;

    @Autowired
    protected BaseService(BaseRepository<T> repository, EntityToDTOMapper<T,R> entityToDtoMapper) {
        this.baseRepository = repository;
        this.repository = (RepositoryClass) repository;
        this.entityToDtoMapper = entityToDtoMapper;
    }

    public BaseRepository<T> getRepository() {
        return baseRepository;
    }


    public R save(BaseEntityDTO<T> entityDTO) {
        return entityToDtoMapper.apply(getRepository().save(entityDTO.toEntity()));
    }

    @Override
    public List<R> findAll() {
        return getRepository().findAll().stream()
                .map(entityToDtoMapper)
                .collect(Collectors.toList());
    }

    public List<R> findAll(Predicate<R> filter) {
        return baseRepository.findAll().stream()
                .map(entityToDtoMapper)
                .filter(filter)
                .collect(Collectors.toList());
    }

    public List<R> findAllAndConvertToDTO() {
        return baseRepository.findAll().stream()
                .map(entityToDtoMapper)
                .collect(Collectors.toList());
    }

    @Override
    public R findById(long id) {
        return getRepository().findById(id)
                .map(entityToDtoMapper)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    public R findByName(String name) {
        return getRepository().findByName(name)
                .map(entityToDtoMapper)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }


    @Override
    public void deleteById(long id) {
        getRepository().deleteById(id);
    }


    // ------------------- Pagination -------------------

    public Page<R> findAllPaginated(Pageable pageable) {
        return baseRepository.findAll().stream()
                .map(entityToDtoMapper)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> PageableExecutionUtils.getPage(list, pageable, list::size)));
    }

    public Page<R> findAllPaginated(Pageable pageable, Predicate<R> filter) {
        return baseRepository.findAll().stream()
                .map(entityToDtoMapper)
                .filter(filter)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> PageableExecutionUtils.getPage(list, pageable, list::size)));
    }

    public Page<R> findAllAndConvertToDTO(Pageable pageable) {
        return baseRepository.findAll().stream()
                .map(entityToDtoMapper)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> PageableExecutionUtils.getPage(list, pageable, list::size)));
    }
    public Page<R> findAllAndConvertToDTO(Pageable pageable, Predicate<T> filter) {
        return baseRepository.findAll().stream()
                .filter(filter)
                .map(entityToDtoMapper)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> PageableExecutionUtils.getPage(list, pageable, list::size)));
    }
}
