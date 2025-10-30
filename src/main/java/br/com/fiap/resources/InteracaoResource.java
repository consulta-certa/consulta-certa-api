package br.com.fiap.resources;

import br.com.fiap.dto.InteracaoRequestDTO;
import br.com.fiap.dto.InteracaoResponseDTO;
import br.com.fiap.services.InteracaoService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.*;

@Path("/interacoes-chatbot")
@Consumes(MediaType.APPLICATION_JSON)
public class InteracaoResource {
    private final InteracaoService service;

    public InteracaoResource() {
        this.service = new InteracaoService();
    }

    @POST
    @Transactional
    public Response insert(InteracaoRequestDTO request, @Context UriInfo uriInfo) {
        InteracaoResponseDTO response = service.insert(request);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(response.id());
        return Response.created(builder.build()).build();
    }
}
