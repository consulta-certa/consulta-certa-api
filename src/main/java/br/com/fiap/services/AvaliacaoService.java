package br.com.fiap.services;

import br.com.fiap.dao.AvaliacaoDAO;
import br.com.fiap.dto.AvaliacaoRequestDTO;
import br.com.fiap.dto.AvaliacaoResponseDTO;
import br.com.fiap.entities.Avaliacao;

public class AvaliacaoService {
    private final AvaliacaoDAO dao;

    public AvaliacaoService() {
        this.dao = new AvaliacaoDAO();
    }

    public AvaliacaoResponseDTO insert(AvaliacaoRequestDTO request) {
        Avaliacao avaliacao = new Avaliacao(
            request.nota(),
            request.comentario(),
            request.dataAvaliacao()
        );
        dao.insertAvaliacao(avaliacao);

        return new AvaliacaoResponseDTO(
          avaliacao.getId().toString()
        );
    }
}
