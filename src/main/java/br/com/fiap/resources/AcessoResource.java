package br.com.fiap.resources;

import br.com.fiap.dto.AcessoRequestDTO;
import br.com.fiap.dto.AcessoResponseDTO;
import br.com.fiap.services.AcessoService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.*;

@Path("/acessos-funcionalidade")
@Consumes(MediaType.APPLICATION_JSON)
public class AcessoResource {
    private final AcessoService service;

    public AcessoResource(AcessoService service) {
        this.service = service;
    }

    @POST
    @Transactional
    public Response insert(AcessoRequestDTO request, @Context UriInfo uriInfo) {
        try {
            AcessoResponseDTO response = service.insert(request);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(response.id().toString());
            return Response.created(builder.build()).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
