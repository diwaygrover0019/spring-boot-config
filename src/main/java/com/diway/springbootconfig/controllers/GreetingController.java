package com.diway.springbootconfig.controllers;

import com.diway.springbootconfig.config.DbSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
public class GreetingController {

    // Providing default value
    @Value("${my.greeting: default value}")
    private String greetingMessage;

    @Value("some static message")
    private String staticMessage;

    @Value("${my.list.values}")
    private List<String> listValues;

    // # tells to evaluate using SPEL (Spring Expression Language)
    @Value("#{${dbValues}}")
    private Map<String, String> dbValues;

    @Autowired
    private DbSettings dbSettings;

    @Autowired
    private Environment env;

    @GetMapping("/greeting")
    public String greeting() {
        //return greetingMessage + staticMessage + listValues + dbValues;
        return greetingMessage + dbSettings.getConnection() + dbSettings.getHost() + dbSettings.getPort();
    }

    @GetMapping("/envdetails")
    public String getEnvDetails() {
        return env.toString();
    }
}
