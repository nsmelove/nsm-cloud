package com.nsm.cloud.app2.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nieshuming on 2018/11/15
 */
@FeignClient("app1")
public interface App1Client {

    @RequestMapping("/")
    String index();
}
