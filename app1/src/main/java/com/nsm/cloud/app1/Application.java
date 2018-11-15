package com.nsm.cloud.app1;

import com.google.common.collect.Maps;
import com.netflix.discovery.EurekaClient;
import com.nsm.cloud.app1.feignclient.App2Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
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
    private App2Client app2Client;
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Autowired
    RestTemplate restTemplate;
    @RequestMapping("/")
    public String home() {
        return "this jdbc url:" +  jdbcUrl;
    }

    @RequestMapping("/app2")
    public String app2() {
        return app2Client.index();
    }
    @RequestMapping("/info")
    public Map info() {
        Map<String,Object> infos = Maps.newHashMap();
        infos.put("allKnownRegions", discoveryClient.getAllKnownRegions().toString());
        infos.put("eurekaClientConfig", discoveryClient.getEurekaClientConfig().toString());
        infos.put("applications", discoveryClient.getApplications());

        return infos;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
