package br.com.fiap.resources;

import br.com.fiap.dto.AcompanhanteRequestDTO;
import br.com.fiap.dto.AcompanhanteResponseDTO;
import br.com.fiap.services.AcompanhanteService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/acompanhantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AcompanhanteResource {
    private final AcompanhanteService service;

    public AcompanhanteResource() {
        this.service = new AcompanhanteService();
    }

    @GET
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") String id) {
        return Response.ok(service.findById(id)).build();
    }

    @POST
    @Transactional
    public Response insert(AcompanhanteRequestDTO request, @Context UriInfo uriInfo) {
        AcompanhanteResponseDTO response = service.insert(request);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(response.id());
        return Response.created(builder.build()).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(AcompanhanteRequestDTO request, @PathParam("id") String id) {
        return Response.ok(service.update(request, id)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") String id) {
        service.delete(id);
        return Response.noContent().build();
    }
}