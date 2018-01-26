package com.andforce.block.http;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class BlockChainApi {

    @GET
    @Path("/{param}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage(@PathParam("param") String msg) {
        return "Hello " + msg + "\n";
    }
}
