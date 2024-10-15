package com.Alek0m0m.library.spring.web.mvc.config;


import com.Alek0m0m.library.spring.web.mvc.base.*;
import com.Alek0m0m.library.spring.web.mvc.event.RelationOrchestrator;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
public class SystemConfig {

    @Autowired
    private List<BaseService<?, ?, ?>> services;

    @Autowired
    private List<BaseRepository<?, ?>> repositories;

    @Autowired
    private List<BaseRESTController<?, ?, ?>> controllers;

    @PostConstruct
    public void registerSystemClasses() {
        services.forEach(service -> RelationOrchestrator.SystemClasses.addService(service.getClass()));
        repositories.forEach(repository -> RelationOrchestrator.SystemClasses.addRepository(repository.getClass()));
        controllers.forEach(controller -> RelationOrchestrator.SystemClasses.addController(controller.getClass()));
    }
}

