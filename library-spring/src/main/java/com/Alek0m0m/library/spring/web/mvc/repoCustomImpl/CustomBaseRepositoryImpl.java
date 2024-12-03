package com.Alek0m0m.library.spring.web.mvc.repoCustomImpl;


import com.Alek0m0m.library.jpa.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

public class CustomBaseRepositoryImpl<T extends BaseEntity> {

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    @Transactional
//    public void resetAutoIncrement(String tableName) {
//        Query query = entityManager.createNativeQuery("ALTER TABLE " + tableName + " AUTO_INCREMENT = 1");
//        query.executeUpdate();
//    }
}
