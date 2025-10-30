package br.com.fiap.services;

import br.com.fiap.dao.AcessoDAO;
import br.com.fiap.dto.AcessoRequestDTO;
import br.com.fiap.dto.AcessoResponseDTO;
import br.com.fiap.entities.Acesso;

import static br.com.fiap.utils.ValidarRequest.verificarNulos;

public class AcessoService {
    private final AcessoDAO dao;

    public AcessoService() {
        this.dao = new AcessoDAO();
    }

    public AcessoResponseDTO insert(AcessoRequestDTO request) {
        verificarNulos(request);
        Acesso acesso = new Acesso(
            request.funcionalidade(),
            request.quantidadeAcessos(),
            request.tempoPermanenciaSeg(),
            request.dataAcesso(),
            request.idPaciente()
        );
        dao.insertAcessos(acesso);
        return new AcessoResponseDTO(
            acesso.getId()
        );
    }
}
