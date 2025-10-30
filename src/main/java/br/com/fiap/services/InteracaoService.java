package br.com.fiap.services;

import br.com.fiap.dao.InteracaoDAO;
import br.com.fiap.dto.InteracaoRequestDTO;
import br.com.fiap.dto.InteracaoResponseDTO;
import br.com.fiap.entities.InteracaoChatbot;

import static br.com.fiap.utils.ValidarRequest.verificarNulos;

public class InteracaoService {
    private final InteracaoDAO dao;

    public InteracaoService() {
        this.dao = new InteracaoDAO();
    }

    public InteracaoResponseDTO insert(InteracaoRequestDTO request) {
        verificarNulos(request);
        InteracaoChatbot interacao = new InteracaoChatbot(
            request.idConversa(),
            request.pergunta(),
            request.dataPergunta()
        );
        dao.insertInteracao(interacao);
        return new InteracaoResponseDTO(
            interacao.getId()
        );
    }
}
