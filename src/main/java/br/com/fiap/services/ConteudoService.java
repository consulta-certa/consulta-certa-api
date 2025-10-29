package br.com.fiap.services;

import br.com.fiap.dao.ConteudoDAO;
import br.com.fiap.dto.ConteudoResponseDTO;
import br.com.fiap.entities.Conteudo;

import java.util.List;

public class ConteudoService {
    private final ConteudoDAO dao;

    public ConteudoService() {
        this.dao = new ConteudoDAO();
    }

    public List<ConteudoResponseDTO> findAll() {
        return dao.findAllConteudo().stream().map(this::toResponse).toList();
    }

    public ConteudoResponseDTO findById(String id) {
        Conteudo conteudo = dao.findByIdConteudo(id);
        return conteudo != null ? toResponse(conteudo) : null;
    }

    private ConteudoResponseDTO toResponse(Conteudo conteudo) {
        return new ConteudoResponseDTO(
            conteudo.getId(),
            conteudo.getTipo(),
            conteudo.getTitulo(),
            conteudo.getTexto(),
            conteudo.getVideo(),
            conteudo.getImagem(),
            conteudo.getDataPublicacao()
        );
    }
}
