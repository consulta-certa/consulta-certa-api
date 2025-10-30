package br.com.fiap.services;

import br.com.fiap.dao.ConsultaDAO;
import br.com.fiap.dto.ConsultaRequestDTO;
import br.com.fiap.dto.ConsultaResponseDTO;
import br.com.fiap.entities.Consulta;
import br.com.fiap.exceptions.EntityNotFoundException;
import br.com.fiap.exceptions.InvalidIdFormatException;

import java.util.List;
import java.util.UUID;

public class ConsultaService {
    private final ConsultaDAO dao;

    public ConsultaService() {
        this.dao = new ConsultaDAO();
    }

    public List<ConsultaResponseDTO> findAll() {
        return dao.findAllConsulta().stream().map(this::toResponse).toList();
    }

    public ConsultaResponseDTO findById(String id) {
        // Regra de negócio: campos id precisam seguir o padrão estabelecido.
        try {
            UUID uuid = UUID.fromString(id);
            Consulta consulta = dao.findByIdConsulta(uuid.toString());

            if (consulta == null) {
                throw new EntityNotFoundException("consulta");
            }
            return toResponse(consulta);

        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }
    }

    public ConsultaResponseDTO insert(ConsultaRequestDTO request) {
        // Regra de negócio: campos id precisam seguir o padrão estabelecido.
        try {
            UUID uuid = UUID.fromString(request.idPaciente());

        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }

        Consulta consulta = new Consulta(
            request.especialidade(),
            request.dataConsulta(),
            request.status(),
            UUID.fromString(request.idPaciente())
        );
        dao.insertConsulta(consulta);

        return toResponse(consulta);
    }

    public ConsultaResponseDTO update(ConsultaRequestDTO request, String id) {
        // Regra de negócio: campos id precisam seguir o padrão estabelecido.
        try {
            UUID uuid = UUID.fromString(id);
            UUID uuidForeign = UUID.fromString(request.idPaciente());

        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }

        if (findById(id) == null) {
            throw new EntityNotFoundException("consulta");
        }

        Consulta consulta = new Consulta(
                request.especialidade(),
                request.dataConsulta(),
                request.status(),
                UUID.fromString(request.idPaciente())
        );
        consulta.setId(UUID.fromString(id));
        dao.updateConsulta(consulta);

        return toResponse(consulta);
    }

    public void delete(String id) {
        try {
            if (findById(id) == null) {
                throw new EntityNotFoundException("consulta");
            }

            dao.deleteConsulta(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }
    }

    private ConsultaResponseDTO toResponse(Consulta consulta) {
        return new ConsultaResponseDTO(
                consulta.getId().toString(),
                consulta.getEspecialidade(),
                consulta.getDataConsulta(),
                consulta.getStatus(),
                consulta.getIdPaciente().toString()
        );
    }
}