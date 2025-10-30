package br.com.fiap.services;

import br.com.fiap.dao.LembreteDAO;
import br.com.fiap.dto.LembreteResponseDTO;
import br.com.fiap.entities.Lembrete;
import br.com.fiap.exceptions.EntityNotFoundException;
import br.com.fiap.exceptions.InvalidIdFormatException;

import java.util.List;
import java.util.UUID;

public class LembreteService {
    private final LembreteDAO dao;

    public LembreteService() {
        this.dao = new LembreteDAO();
    }

    public List<LembreteResponseDTO> findAll() {
        return dao.findAllLembrete().stream().map(this::toResponse).toList();
    }

    public LembreteResponseDTO findById(String id) {
        // Regra de negócio: campos id precisam seguir o padrão estabelecido.
        try {
            UUID uuid = UUID.fromString(id);
            Lembrete lembrete = dao.findByIdLembrete(uuid.toString());
            if (lembrete == null) {
                throw new EntityNotFoundException("lembrete");
            }
            return toResponse(lembrete);

        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }

    }

    private LembreteResponseDTO toResponse(Lembrete lembrete) {
        return new LembreteResponseDTO(
            lembrete.getId().toString(),
            lembrete.getDataEnvio(),
            lembrete.getIdConsulta().toString()
        );
    }
}
