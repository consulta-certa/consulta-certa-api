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
        try {
            return Response.ok(service.findAll()).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(String id) {
        try {
            return Response.ok(service.findById(id)).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Transactional
    public Response insert(AcompanhanteRequestDTO request, @Context UriInfo uriInfo) {
        try {
            AcompanhanteResponseDTO response = service.insert(request);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(response.id().toString());
            return Response.created(builder.build()).entity(response).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(AcompanhanteRequestDTO request) {
        try {
            return Response.ok(service.update(request)).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(String id) {
        try {
            service.delete(id);
            return Response.noContent().build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}