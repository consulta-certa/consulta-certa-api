package br.com.fiap.resources;

import br.com.fiap.dto.AcompanhanteRequestDTO;
import br.com.fiap.dto.AcompanhanteResponseDTO;
import br.com.fiap.services.AcompanhanteService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.util.List;

@Path("/acompanhantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AcompanhanteResource {
    private final AcompanhanteService service;

    public AcompanhanteResource(AcompanhanteService service) {
        this.service = service;
    }

    @GET
    public Response findAll() {
        try {
            List<AcompanhanteResponseDTO> response = service.findAll();
            return Response.ok(response).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("{id}")
    public Response findById(String id) {
        try {
            AcompanhanteResponseDTO response = service.findById(id);
            return Response.ok(response).build();

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
            return Response.created(uriInfo.getAbsolutePath()).entity(response).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(AcompanhanteRequestDTO request) {
        try {
            AcompanhanteResponseDTO response = service.update(request);
            return Response.ok(response).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("{id}")
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