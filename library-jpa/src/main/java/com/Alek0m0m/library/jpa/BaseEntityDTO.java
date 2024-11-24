package com.Alek0m0m.library.jpa;

import org.springframework.stereotype.Component;

@Component
public abstract class BaseEntityDTO<T extends BaseEntity>{

    private long id;

    public abstract  T toEntity();

    public long getId() {
        return id;
    }

    public BaseEntityDTO<T> setId(long id) {
        this.id = id;
        return this;
    }
}
