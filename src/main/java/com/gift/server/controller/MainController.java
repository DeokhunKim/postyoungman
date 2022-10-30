package com.gift.server.controller;

import com.gift.server.config.Config;
import com.gift.server.publish.scheduler.Scheduler;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private Config config;

    @Autowired
    private Scheduler scheduler;

    @GetMapping("/")
    public String home() {

        scheduler.doSomething();

        System.out.println("config = " + config.getInterval());
        return "ok";
    }
}
