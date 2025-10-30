package br.com.fiap.resources;

import br.com.fiap.dto.PacienteRequestDTO;
import br.com.fiap.dto.PacienteResponseDTO;
import br.com.fiap.services.PacienteService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteResource {
    private final PacienteService service;

    public PacienteResource() {
        this.service = new PacienteService();
    }

    @GET
    public Response findAll () {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById (@PathParam("id") String id) {
        return Response.ok(service.findById(id)).build();
    }

    @POST
    @Transactional
    public Response insert (PacienteRequestDTO request, @Context UriInfo uriInfo) {
        PacienteResponseDTO response = service.insert(request);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(response.id());
        return Response.created(builder.build()).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update (PacienteRequestDTO request, @PathParam("id") String id) {
        return Response.ok(service.update(request, id)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete (@PathParam("id") String id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
