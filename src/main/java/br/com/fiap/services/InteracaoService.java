package br.com.fiap.services;

import br.com.fiap.dao.InteracaoDAO;
import br.com.fiap.dto.InteracaoRequestDTO;
import br.com.fiap.dto.InteracaoResponseDTO;
import br.com.fiap.entities.InteracaoChatbot;

public class InteracaoService {
    private final InteracaoDAO dao;

    public InteracaoService(InteracaoDAO dao) {
        this.dao = dao;
    }

    public InteracaoResponseDTO insert(InteracaoRequestDTO request) {
        InteracaoChatbot interacao = new InteracaoChatbot(
            request.pergunta(),
            request.dataPergunta()
        );
        dao.insertInteracao(interacao);
        return new InteracaoResponseDTO(
            interacao.getId()
        );
    }
}
