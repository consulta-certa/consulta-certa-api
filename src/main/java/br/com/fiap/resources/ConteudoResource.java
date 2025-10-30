package br.com.fiap.resources;

import br.com.fiap.services.ConsultaService;
import br.com.fiap.services.ConteudoService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/conteudos")
@Produces(MediaType.APPLICATION_JSON)
public class ConteudoResource {
    private final ConteudoService service;

    public ConteudoResource() {
        this.service = new ConteudoService();
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
}
