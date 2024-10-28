package com.Alek0m0m.library.jpa;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public abstract class BaseEntityDTO<T extends BaseEntity>{

    private long id;

    public abstract  <T extends BaseEntity> T toEntity();

}
