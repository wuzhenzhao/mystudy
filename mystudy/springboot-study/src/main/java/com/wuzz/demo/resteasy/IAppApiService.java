package com.wuzz.demo.resteasy;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/api/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IAppApiService {

    @GET
    @Path("/test")
    String test();

}
