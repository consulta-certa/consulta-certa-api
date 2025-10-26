package br.com.fiap.resources;

import br.com.fiap.dto.ConsultaRequestDTO;
import br.com.fiap.dto.ConsultaResponseDTO;
import br.com.fiap.services.ConsultaService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/consultas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultaResource {
    private final ConsultaService service;

    public ConsultaResource() {
        this.service = new ConsultaService();
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
            return Response.status(Response.Status.NOT_FOUND).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Transactional
    public Response insert(ConsultaRequestDTO request, @Context UriInfo uriInfo) {
        try {
            ConsultaResponseDTO response = service.insert(request);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(response.id().toString());
            return Response.created(builder.build()).entity(response).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(ConsultaRequestDTO request, @Context UriInfo uriInfo) {
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
