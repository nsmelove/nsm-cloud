package com.nsm.cloud.app2;

import com.google.common.collect.Maps;
import com.netflix.discovery.EurekaClient;
import com.nsm.cloud.app2.feignclient.App1Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by nieshuming on 2018/11/7
 */
@SpringBootApplication
@RestController
@EnableFeignClients
public class Application {
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Autowired
    private EurekaClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private App1Client app1Client;
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @RequestMapping("/")
    public String home() {
        return "this jdbc url:" +  jdbcUrl;
    }

    @RequestMapping("/app1")
    public String app1() {
        return app1Client.index();
    }
    @RequestMapping("/info")
    public Map info() {
        Map<String,Object> infos = Maps.newHashMap();
        infos.put("allKnownRegions", discoveryClient.getAllKnownRegions().toString());
        infos.put("eurekaClientConfig", discoveryClient.getEurekaClientConfig().toString());
        infos.put("applications", discoveryClient.getApplications().toString());

        return infos;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
