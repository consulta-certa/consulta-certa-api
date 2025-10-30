package br.com.fiap.services;

import br.com.fiap.dao.ContatoDAO;
import br.com.fiap.dto.ContatoResponseDTO;
import br.com.fiap.entities.Contato;
import br.com.fiap.exceptions.EntityNotFoundException;
import br.com.fiap.exceptions.InvalidIdFormatException;

import java.util.List;
import java.util.UUID;

public class ContatoService {
    private final ContatoDAO dao;

    public ContatoService() {
        this.dao = new ContatoDAO();
    }

    public List<ContatoResponseDTO> findAll() {
        return dao.findAllContato().stream().map(this::toResponse).toList();
    }

    public ContatoResponseDTO findById(String id) {
        // Regra de negócio: campos id precisam seguir o padrão estabelecido.
        try {
            UUID uuid = UUID.fromString(id);
            Contato contato = dao.findByIdContato(uuid.toString());
            if (contato == null) {
                throw new EntityNotFoundException("contato");
            }
            return toResponse(contato);

        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }
    }

    private ContatoResponseDTO toResponse(Contato contato) {
        return new ContatoResponseDTO(
            contato.getId().toString(),
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
