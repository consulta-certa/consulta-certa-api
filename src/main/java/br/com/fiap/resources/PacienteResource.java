package br.com.fiap.resources;

import br.com.fiap.dto.PacienteRequestDTO;
import br.com.fiap.services.PacienteService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

@Path("/pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteResource {
    private final PacienteService service;

    public PacienteResource(PacienteService service) {
        this.service = service;
    }

    @GET
    public Response findAll () {
        try {
            return Response.ok(service.findAll()).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("{id}")
    public Response findById (String id) {
        try {
            return Response.ok(service.findById(id)).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Transactional
    public Response insert (PacienteRequestDTO request, @Context UriInfo uriInfo) {
        try {
            return Response.created(uriInfo.getAbsolutePath()).entity(service.insert(request)).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update (PacienteRequestDTO request) {
        try {
            return Response.ok(service.update(request)).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete (String id) {
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
