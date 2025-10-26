package br.com.fiap.services;

import br.com.fiap.dao.ConsultaDAO;
import br.com.fiap.dto.ConsultaRequestDTO;
import br.com.fiap.dto.ConsultaResponseDTO;
import br.com.fiap.entities.Consulta;

import java.util.List;

public class ConsultaService {
    private final ConsultaDAO dao;

    public ConsultaService() {
        this.dao = new ConsultaDAO();
    }

    public List<ConsultaResponseDTO> findAll() {
        return dao.findAllCosulta().stream().map(this::toResponse).toList();
    }

    public ConsultaResponseDTO findById(String id) {
        Consulta consulta = dao.findByIdConsulta(id);
        return consulta != null ? toResponse(consulta) : null;
    }

    public ConsultaResponseDTO insert(ConsultaRequestDTO request) {
        Consulta consulta = new Consulta(
            request.especialidade(),
            request.dataConsulta(),
            request.status(),
            request.idPaciente()
        );
        dao.insertConsulta(consulta);
        return new ConsultaResponseDTO(
            consulta.getId(),
            consulta.getEspecialidade(),
            consulta.getDataConsulta(),
            consulta.getStatus(),
            consulta.getIdPaciente()
        );
    }

    public ConsultaResponseDTO update(ConsultaRequestDTO request) {
        Consulta consulta = new Consulta(
                request.especialidade(),
                request.dataConsulta(),
                request.status(),
                request.idPaciente()
        );
        dao.updateConsulta(consulta);
        return new ConsultaResponseDTO(
                consulta.getId(),
                consulta.getEspecialidade(),
                consulta.getDataConsulta(),
                consulta.getStatus(),
                consulta.getIdPaciente()
        );
    }

    public void delete(String id) {
        if (findById(id) == null) {
            throw new RuntimeException("Consulta não encontrada");
        }

        dao.deleteConsulta(id);
    }

    private ConsultaResponseDTO toResponse(Consulta consulta) {
        return new ConsultaResponseDTO(
                consulta.getId(),
                consulta.getEspecialidade(),
                consulta.getDataConsulta(),
                consulta.getStatus(),
                consulta.getIdPaciente()
        );
    }
}