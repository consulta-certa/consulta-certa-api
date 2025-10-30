package br.com.fiap.services;

import br.com.fiap.dao.ConteudoDAO;
import br.com.fiap.dto.ConteudoResponseDTO;
import br.com.fiap.entities.Conteudo;
import br.com.fiap.exceptions.EntityNotFoundException;
import br.com.fiap.exceptions.InvalidIdFormatException;

import java.util.List;
import java.util.UUID;

public class ConteudoService {
    private final ConteudoDAO dao;

    public ConteudoService() {
        this.dao = new ConteudoDAO();
    }

    public List<ConteudoResponseDTO> findAll() {
        return dao.findAllConteudo().stream().map(this::toResponse).toList();
    }

    public ConteudoResponseDTO findById(String id) {
        // Regra de negócio: campos id precisam seguir o padrão estabelecido.
        try {
            UUID uuid = UUID.fromString(id);
            Conteudo conteudo = dao.findByIdConteudo(uuid.toString());
            if (conteudo == null) {
                throw new EntityNotFoundException("conteudo");
            }
            return toResponse(conteudo);

        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }
    }

    private ConteudoResponseDTO toResponse(Conteudo conteudo) {
        return new ConteudoResponseDTO(
            conteudo.getId().toString(),
            conteudo.getTipo(),
            conteudo.getTitulo(),
            conteudo.getTexto(),
            conteudo.getVideo(),
            conteudo.getImagem(),
            conteudo.getDataPublicacao()
        );
    }
}
