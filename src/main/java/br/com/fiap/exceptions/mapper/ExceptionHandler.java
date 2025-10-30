package br.com.fiap.exceptions.mapper;

import br.com.fiap.dto.ErroResponseDTO;
import br.com.fiap.exceptions.*;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable e) {
        if (e instanceof DatabaseException) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErroResponseDTO(
                e.getMessage() + "Causa: " + e.getCause().getMessage().replace("RM566315.", "")
            )).build();
        }

        if (e instanceof EntityNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErroResponseDTO(e.getMessage())).build();
        }

        if (e instanceof InvalidIdFormatException) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErroResponseDTO(e.getMessage())).build();
        }

        if (e instanceof MissingFieldException) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErroResponseDTO(e.getMessage())).build();
        }

        if (e instanceof NotAllowedException) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(new ErroResponseDTO(
                "|ERRO|: requisição indisponível para este endpoint.")
            ).build();
        }

        if (e instanceof NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErroResponseDTO(
                "|ERRO|: endpoint não encontrado.")
            ).build();
        }

        return Response.serverError().entity(new ErroResponseDTO("|ERRO|: erro inesperado, verifique disponibilidade do servidor.")).build();
    }
}
