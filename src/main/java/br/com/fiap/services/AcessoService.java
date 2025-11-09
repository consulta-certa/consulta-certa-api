package br.com.fiap.services;

import br.com.fiap.dao.AcessoDAO;
import br.com.fiap.dto.AcessoRequestDTO;
import br.com.fiap.dto.AcessoResponseDTO;
import br.com.fiap.entities.Acesso;
import br.com.fiap.exceptions.InvalidIdFormatException;

import java.util.UUID;

public class AcessoService {
    private final AcessoDAO dao;

    public AcessoService() {
        this.dao = new AcessoDAO();
    }

    public AcessoResponseDTO insert(AcessoRequestDTO request) {
        // Regra de negócio: campos id precisam seguir o padrão estabelecido.
        UUID uuidForeign = null;
        try {
            if (request.idPaciente() != null) {
                uuidForeign = UUID.fromString(request.idPaciente());
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }

        Acesso acesso = new Acesso(
            request.funcionalidade(),
            request.quantidadeAcessos(),
            request.tempoPermanenciaSeg(),
            request.dataAcesso(),
            uuidForeign
        );
        dao.insertAcessos(acesso);

        return new AcessoResponseDTO(
            acesso.getId().toString()
        );
    }
}
