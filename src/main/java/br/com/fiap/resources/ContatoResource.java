package br.com.fiap.resources;

import br.com.fiap.services.ContatoService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/contatos_hc")
@Produces(MediaType.APPLICATION_JSON)
public class ContatoResource {
    private final ContatoService service;

    public ContatoResource() {
        this.service = new ContatoService();
    }

    @GET
    public Response findAll() {
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") String id){
        return Response.ok(service.findById(id)).build();
    }
}
