package com.hbelmiro.configserver.resource;

import com.hbelmiro.configserver.model.Configuration;
import io.quarkus.panache.common.Sort;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/configurations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConfigurationResource {

    @GET
    public Response get() {
        return Response.ok(Configuration.listAll(Sort.by("_id"))).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") String id) {
        Configuration entity = Configuration.findById(id);

        if (entity != null) {
            return Response.ok(entity).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    public void put(Configuration configuration) {
        configuration.persistOrUpdate();
    }

    @POST
    public void post(Configuration configuration) {
        configuration.persist();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id) {
        if (Configuration.deleteById(id)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}