package com.Alek0m0m.library.spring.web.mvc.repoCustomImpl;


import com.Alek0m0m.library.jpa.BaseEntity;

public interface CustomBaseRepository<T extends BaseEntity> {
    void resetAutoIncrement(String tableName);
}
