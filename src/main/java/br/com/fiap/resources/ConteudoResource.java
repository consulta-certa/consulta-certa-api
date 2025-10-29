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
        try {
            return Response.ok(service.findAll()).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById (@PathParam("id") String id) {
        try {
            return Response.ok(service.findById(id)).build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
