package com.wuzz.demo.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/8/14
 * Time: 15:42
 * Description 描述:
 */
@Path( "/user")
public interface UserService {
    @GET
    @Path( "/register/{id}")
    int register(@PathParam( "id") int id);
}