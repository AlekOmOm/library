package com.Alek0m0m.library.jpa;

import org.springframework.stereotype.Component;

@Component
public abstract class BaseEntityDTO<T extends BaseEntity>{

    private Long id;

    public abstract T toEntity();

    public Long getId() {
        return id;
    }

    public BaseEntityDTO<T> setId(Long id) {
        this.id = id;
        return this;
    }
}
