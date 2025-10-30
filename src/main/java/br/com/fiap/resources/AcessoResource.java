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

    public AcessoResource() {
        this.service = new AcessoService();
    }

    @POST
    @Transactional
    public Response insert(AcessoRequestDTO request, @Context UriInfo uriInfo) {
        AcessoResponseDTO response = service.insert(request);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(response.id());
        return Response.created(builder.build()).build();
    }
}
