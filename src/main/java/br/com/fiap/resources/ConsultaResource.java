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
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById (@PathParam("id") String id) {
        return Response.ok(service.findById(id)).build();
    }

    @POST
    @Transactional
    public Response insert(ConsultaRequestDTO request, @Context UriInfo uriInfo) {
        ConsultaResponseDTO response = service.insert(request);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(response.id().toString());
        return Response.created(builder.build()).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(ConsultaRequestDTO request, @PathParam("id") String id, @Context UriInfo uriInfo) {
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
