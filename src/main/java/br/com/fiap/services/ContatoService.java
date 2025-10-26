package br.com.fiap.services;

import br.com.fiap.dao.ContatoDAO;
import br.com.fiap.dto.ContatoResponseDTO;
import br.com.fiap.entities.Contato;

import java.util.List;

public class ContatoService {
    private final ContatoDAO dao;

    public ContatoService(ContatoDAO dao) {
        this.dao = dao;
    }

    public List<ContatoResponseDTO> findAll() {
        return dao.findAllContato().stream().map(this::toResponse).toList();
    }

    public ContatoResponseDTO findById(String id) {
        Contato contato = dao.findByIdContato(id);
        return contato != null ? toResponse(contato) : null;
    }

    private ContatoResponseDTO toResponse(Contato contato) {
        return new ContatoResponseDTO(
            contato.getId(),
            contato.getNome(),
            contato.getEmail(),
            contato.getTelefone(),
            contato.getNumero(),
            contato.getRua(),
            contato.getBairro(),
            contato.getCidade(),
            contato.getCep()
        );
    }
}
