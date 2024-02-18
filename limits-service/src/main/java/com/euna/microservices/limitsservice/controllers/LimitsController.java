package com.euna.microservices.limitsservice.controllers;

import com.euna.microservices.limitsservice.beans.Limit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Limit configuration;

    @GetMapping("/limits")
    public Limit getLimits() {
        return new Limit(configuration.getMin(),
                configuration.getMax());
    }
}
