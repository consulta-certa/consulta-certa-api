package br.com.fiap.resources;

import br.com.fiap.services.LembreteService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/lembretes")
@Produces(MediaType.APPLICATION_JSON)
public class LembreteResource {
    private final LembreteService service;

    public LembreteResource() {
        this.service = new LembreteService();
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
}
