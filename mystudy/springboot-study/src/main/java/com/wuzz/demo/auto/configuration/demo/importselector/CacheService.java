package com.wuzz.demo.auto.configuration.demo.importselector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class CacheService {

    @Autowired
    private LoggerService loggerService;
    public String login(){
        return "heloo";
    }
}
