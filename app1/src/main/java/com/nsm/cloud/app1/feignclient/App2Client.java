package com.nsm.cloud.app1.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nieshuming on 2018/11/15
 */
@FeignClient("app2")
public interface App2Client {

    @RequestMapping("/")
    String index();
}
