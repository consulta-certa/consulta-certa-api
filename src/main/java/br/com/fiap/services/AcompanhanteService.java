package br.com.fiap.services;

import br.com.fiap.dao.AcompanhanteDAO;
import br.com.fiap.dto.AcompanhanteRequestDTO;
import br.com.fiap.dto.AcompanhanteResponseDTO;
import br.com.fiap.entities.Acompanhante;
import br.com.fiap.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

import static br.com.fiap.utils.ValidarRequest.verificarNulos;

public class AcompanhanteService {
    private final AcompanhanteDAO dao;

    public AcompanhanteService() {
        this.dao = new AcompanhanteDAO();
    }

    public List<AcompanhanteResponseDTO> findAll() {
        return dao.findAllAcompanhante().stream().map(this::toResponse).toList();
    }

    public AcompanhanteResponseDTO findById (String id) {
        Acompanhante acompanhante = dao.findByIdAcompanhante(id);
        return acompanhante != null ? toResponse(acompanhante) : null;
    }

    public AcompanhanteResponseDTO insert(AcompanhanteRequestDTO request) {
        verificarNulos(request);
        Acompanhante acompanhante = new Acompanhante(
            request.nome(),
            request.email(),
            request.telefone(),
            request.parentesco(),
            request.idPaciente()
        );
        dao.insertAcompanhante(acompanhante);

        return new AcompanhanteResponseDTO(
            acompanhante.getId(),
            acompanhante.getNome(),
            acompanhante.getEmail(),
            acompanhante.getTelefone(),
            acompanhante.getParentesco(),
            acompanhante.getIdPaciente()
        );
    }

    public AcompanhanteResponseDTO update(AcompanhanteRequestDTO request, String id) {
        if (findById(id) == null) {
            throw new EntityNotFoundException("acompanhante");
        }
        verificarNulos(request);
        Acompanhante acompanhante = new Acompanhante(
            request.nome(),
            request.email(),
            request.telefone(),
            request.parentesco(),
            request.idPaciente()
        );
        acompanhante.setId(UUID.fromString(id));
        dao.updateAcompanhante(acompanhante);
        return new AcompanhanteResponseDTO(
            acompanhante.getId(),
            acompanhante.getNome(),
            acompanhante.getEmail(),
            acompanhante.getTelefone(),
            acompanhante.getParentesco(),
            acompanhante.getIdPaciente()
        );
    }

    public void delete(String id) {
        if (findById(id) == null) {
            throw new EntityNotFoundException("acompanhante");
        }

        dao.deleteAcompanhante(id);
    }

    private AcompanhanteResponseDTO toResponse(Acompanhante acompanhante) {
        return new AcompanhanteResponseDTO(
            acompanhante.getId(),
            acompanhante.getNome(),
            acompanhante.getEmail(),
            acompanhante.getTelefone(),
            acompanhante.getParentesco(),
            acompanhante.getIdPaciente()
        );
    }
}
