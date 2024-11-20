package com.Alek0m0m.library.jpa;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


import java.io.Serializable;

@MappedSuperclass

public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (id != 0) {
            this.id = id;
        }
    }
}
