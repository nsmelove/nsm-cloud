package com.nsm.cloud.appf1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Created by nieshuming on 2018/11/27
 */
@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("/")
    public Mono<String> index() {

        return Mono.just("hello appf1");
    }

    @RequestMapping("/info")
    public String info() {
        return "info appf1";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
