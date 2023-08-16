package org.ecommerce.service.api;

import org.ecommerce.domain.model.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/productmanagement/v1/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ProductRestService {

    @GET
    @Path("")
    public Response getAllProducts();
    /*
     * To save products to database
     */
    @POST
    @Path("")
    public Response saveProduct(Product product);

    @POST
    @Path("/update/{id}")
    public Response updateProduct(@PathParam("id") int id, Product product);

    @POST
    @Path("/quantity/{id}")
    public Response updateProductQuantity(@PathParam("id") int id);

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") int id);

    @GET
    @Path("/category")
    public Response getProductsByCategory(@QueryParam("name") String category);

    @GET
    @Path("/categories")
    public Response getAllCategories();
}
