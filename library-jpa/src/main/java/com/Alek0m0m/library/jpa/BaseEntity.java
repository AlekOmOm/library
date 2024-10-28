package com.Alek0m0m.library.jpa;

import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Entity
@Data
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;


}
