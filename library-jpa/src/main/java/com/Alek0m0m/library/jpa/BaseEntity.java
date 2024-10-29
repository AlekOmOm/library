package com.Alek0m0m.library.jpa;


import jakarta.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Entity

public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
