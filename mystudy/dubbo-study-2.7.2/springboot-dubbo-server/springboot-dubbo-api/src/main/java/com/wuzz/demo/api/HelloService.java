package com.wuzz.demo.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/16
 * Time: 14:18
 * Description 描述:
 */
@Path("/")
public interface HelloService {

    @GET
    @Path("/sayRest")
    String sayHello() throws Exception;
}
