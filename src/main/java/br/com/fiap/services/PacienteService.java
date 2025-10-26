package br.com.fiap.services;

import br.com.fiap.dao.PacienteDAO;
import br.com.fiap.dto.PacienteRequestDTO;
import br.com.fiap.dto.PacienteResponseDTO;
import br.com.fiap.entities.Paciente;

import java.util.List;

public class PacienteService {
    private final PacienteDAO dao;

    public PacienteService() {
        this.dao = new PacienteDAO();
    }

    public List<PacienteResponseDTO> findAll() {
        return dao.findAllPaciente().stream().map(this::toResponse).toList();
    }

    public PacienteResponseDTO findById(String id) {
        return toResponse(dao.findByIdPaciente(id));
    }

    public PacienteResponseDTO insert(PacienteRequestDTO request) {
        Paciente paciente = new Paciente(
            request.nome(),
            request.email(),
            request.senha(),
            request.telefone(),
            request.acompanhante()
        );
        dao.insertPaciente(paciente);
        return new PacienteResponseDTO(
            paciente.getId(),
            paciente.getNome(),
            paciente.getEmail(),
            paciente.getSenha(),
            paciente.getTelefone(),
            paciente.getAcompanhante()
        );
    }

    public PacienteResponseDTO update(PacienteRequestDTO request) {
        Paciente paciente = new Paciente(
            request.nome(),
            request.email(),
            request.senha(),
            request.telefone(),
            request.acompanhante()
        );
        dao.updatePaciente(paciente);
        return new PacienteResponseDTO(
            paciente.getId(),
            paciente.getNome(),
            paciente.getEmail(),
            paciente.getSenha(),
            paciente.getTelefone(),
            paciente.getAcompanhante()
        );
    }

    public void delete(String id) {
        if (dao.findByIdPaciente(id) == null) {
            throw new RuntimeException("Paciente não encontrado");
        }
        dao.deletePaciente(id);
    }

    private PacienteResponseDTO toResponse(Paciente paciente) {
        return new PacienteResponseDTO(
            paciente.getId(),
            paciente.getNome(),
            paciente.getEmail(),
            paciente.getSenha(),
            paciente.getTelefone(),
            paciente.getAcompanhante()
        );
    }
}
