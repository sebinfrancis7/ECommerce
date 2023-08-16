package org.ecommerce.service.api;

import org.jboss.logging.annotations.Pos;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cartmanagement/v1/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CartRestService {
    @GET
    public Response getCart();

    @POST()
    @Path("/add")
    public Response addCartEntry(@QueryParam("id") int id);

    @DELETE()
    @Path("/remove")
    public Response removeCartItem(@QueryParam("id") int id);

    @DELETE
    @Path("/remove/{id}")
    public Response removeCartEntry(@PathParam("id") int id);

    @POST
    @Path("/edit")
    public Response editCartEntryCount(@QueryParam("entryId") int id, @QueryParam("count") int count);

//    @GET
//    @Path("/size")
//    public Response getCartSize();
}
