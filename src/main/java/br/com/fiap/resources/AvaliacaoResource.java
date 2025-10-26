package br.com.fiap.resources;

import br.com.fiap.dto.AvaliacaoRequestDTO;
import br.com.fiap.dto.AvaliacaoResponseDTO;
import br.com.fiap.services.AvaliacaoService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.*;

@Path("/avalicoes")
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoResource {
    private final AvaliacaoService service;

    public AvaliacaoResource(AvaliacaoService service) {
        this.service = service;
    }

    @POST
    @Transactional
    public Response insert(AvaliacaoRequestDTO request, @Context UriInfo uriInfo) {
        try {
            AvaliacaoResponseDTO response = service.insert(request);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(response.id().toString());
            return Response.created(builder.build()).build();

        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
