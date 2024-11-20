package com.Alek0m0m.library.jpa;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;



@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntityDTO<T extends BaseEntity>{

    private long id;

    public abstract  T toEntity();

}
