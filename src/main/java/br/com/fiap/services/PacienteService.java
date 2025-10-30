package br.com.fiap.services;

import br.com.fiap.dao.PacienteDAO;
import br.com.fiap.dto.PacienteRequestDTO;
import br.com.fiap.dto.PacienteResponseDTO;
import br.com.fiap.entities.Paciente;
import br.com.fiap.exceptions.EntityNotFoundException;
import br.com.fiap.exceptions.InvalidIdFormatException;
import java.util.List;
import java.util.UUID;

public class PacienteService {
    private final PacienteDAO dao;

    public PacienteService() {
        this.dao = new PacienteDAO();
    }

    public List<PacienteResponseDTO> findAll() {
        return dao.findAllPaciente().stream().map(this::toResponse).toList();
    }

    public PacienteResponseDTO findById(String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Paciente paciente = dao.findByIdPaciente(uuid.toString());
            if (paciente == null) {
                throw new EntityNotFoundException("paciente");
            }
            return toResponse(paciente);

        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }
    }

    public PacienteResponseDTO insert(PacienteRequestDTO request) {
        Paciente paciente = new Paciente(
            request.nome(),
            request.email(),
            request.senha(),
            request.telefone(),
            request.acompanhantes()
        );
        dao.insertPaciente(paciente);

        return toResponse(paciente);
    }

    public PacienteResponseDTO update(PacienteRequestDTO request, String id) {
        // Regra de negócio: campos id precisam seguir o padrão estabelecido.
        try {
            if (findById(id) == null) {
                throw new EntityNotFoundException("pacientee");
            }

            Paciente paciente = new Paciente(
                    request.nome(),
                    request.email(),
                    request.senha(),
                    request.telefone(),
                    request.acompanhantes()
            );
            paciente.setId(UUID.fromString(id));
            dao.updatePaciente(paciente);

            return toResponse(paciente);

        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }
    }

    public void delete(String id) {
        try {
            if (findById(id) == null) {
                throw new EntityNotFoundException("pacientee");
            }
            dao.deletePaciente(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }
    }

    private PacienteResponseDTO toResponse(Paciente paciente) {
        return new PacienteResponseDTO(
            paciente.getId().toString(),
            paciente.getNome(),
            paciente.getEmail(),
            paciente.getSenha(),
            paciente.getTelefone(),
            paciente.getAcompanhantes()
        );
    }
}
