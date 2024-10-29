package com.Alek0m0m.library.jpa;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public abstract class BaseEntityDTO<T extends BaseEntity>{

    private long id;

    public abstract  T toEntity();

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
