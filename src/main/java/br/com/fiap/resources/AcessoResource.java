package br.com.fiap.resources;

import br.com.fiap.dto.AcessoRequestDTO;
import br.com.fiap.services.AcessoService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/acessos-funcionalidade")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AcessoResource {
    private final AcessoService service;

    public AcessoResource(AcessoService service) {
        this.service = service;
    }

    @POST
    @Transactional
    public Response inserirAcesso (AcessoRequestDTO request) {
        try {
            service.insertAcessos(request);
            return Response.status(Response.Status.CREATED).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
