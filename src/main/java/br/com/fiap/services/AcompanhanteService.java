package br.com.fiap.services;

import br.com.fiap.dao.AcompanhanteDAO;
import br.com.fiap.dto.AcompanhanteRequestDTO;
import br.com.fiap.dto.AcompanhanteResponseDTO;
import br.com.fiap.entities.Acompanhante;
import br.com.fiap.exceptions.EntityNotFoundException;
import br.com.fiap.exceptions.InvalidIdFormatException;

import java.util.List;
import java.util.UUID;

public class AcompanhanteService {
    private final AcompanhanteDAO dao;

    public AcompanhanteService() {
        this.dao = new AcompanhanteDAO();
    }

    public List<AcompanhanteResponseDTO> findAll() {
        return dao.findAllAcompanhante().stream().map(this::toResponse).toList();
    }

    public AcompanhanteResponseDTO findById (String id) {
        // Regra de negócio: campos id precisam seguir o padrão estabelecido.
        try {
            UUID uuid = UUID.fromString(id);
            Acompanhante acompanhante = dao.findByIdAcompanhante(uuid.toString());

            if (acompanhante == null) {
                throw new EntityNotFoundException("acompanhante");
            }
            return toResponse(acompanhante);

        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }
    }

    public AcompanhanteResponseDTO insert(AcompanhanteRequestDTO request) {
        // Regra de negócio: campos id precisam seguir o padrão estabelecido.
        try {
            UUID uuid = UUID.fromString(request.idPaciente());

        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }

        Acompanhante acompanhante = new Acompanhante(
            request.nome(),
            request.email(),
            request.telefone(),
            request.parentesco(),
            UUID.fromString(request.idPaciente())
        );
        dao.insertAcompanhante(acompanhante);

        return toResponse(acompanhante);
    }

    public AcompanhanteResponseDTO update(AcompanhanteRequestDTO request, String id) {
        // Regra de negócio: campos id precisam seguir o padrão estabelecido.
        try {
            UUID uuid = UUID.fromString(id);
            UUID uuidForeign = UUID.fromString(request.idPaciente());

        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }

        if (findById(id) == null) {
            throw new EntityNotFoundException("acompanhante");
        }

        Acompanhante acompanhante = new Acompanhante(
                request.nome(),
                request.email(),
                request.telefone(),
                request.parentesco(),
                UUID.fromString(request.idPaciente())
        );
        acompanhante.setId(UUID.fromString(id));
        dao.updateAcompanhante(acompanhante);

        return toResponse(acompanhante);
    }

    public void delete(String id) {
        try {
            if (findById(id) == null) {
                throw new EntityNotFoundException("acompanhante");
            }
            dao.deleteAcompanhante(id);

        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }
    }

    private AcompanhanteResponseDTO toResponse(Acompanhante acompanhante) {
        return new AcompanhanteResponseDTO(
            acompanhante.getId().toString(),
            acompanhante.getNome(),
            acompanhante.getEmail(),
            acompanhante.getTelefone(),
            acompanhante.getParentesco(),
            acompanhante.getIdPaciente().toString()
        );
    }
}
