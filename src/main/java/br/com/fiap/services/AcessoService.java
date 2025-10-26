package br.com.fiap.services;

import br.com.fiap.dao.AcessoDAO;
import br.com.fiap.dto.AcessoRequestDTO;
import br.com.fiap.entities.Acesso;

public class AcessoService {
    private final AcessoDAO dao;

    public AcessoService(AcessoDAO dao) {
        this.dao = dao;
    }

    public void insertAcessos(AcessoRequestDTO request) {
        Acesso acesso = new Acesso(
            request.funcionalidade(),
            request.quantidadeAcessos(),
            request.tempoPermanenciaSeg(),
            request.dataAcesso(),
            request.idPaciente()
        );

        dao.insertAcessos(acesso);
    }
}
