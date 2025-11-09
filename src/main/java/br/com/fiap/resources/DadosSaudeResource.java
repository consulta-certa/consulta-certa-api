package br.com.fiap.resources;

import br.com.fiap.dto.DadosSaudeRequestDTO;
import br.com.fiap.dto.DadosSaudeResponseDTO;
import br.com.fiap.services.DadosSaudeService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/dados_saude_pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DadosSaudeResource {
    private final DadosSaudeService service;

    public DadosSaudeResource() {
        this.service = new DadosSaudeService();
    }

    @POST
    @Transactional
    public Response insert(DadosSaudeRequestDTO request, @Context UriInfo uriInfo) {
        DadosSaudeResponseDTO response = service.insert(request);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(response.id());
        return Response.created(builder.build()).entity(response).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete (@PathParam("id") String id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
