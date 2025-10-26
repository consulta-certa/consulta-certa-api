package br.com.fiap.services;

import br.com.fiap.dao.LembreteDAO;
import br.com.fiap.dto.LembreteResponseDTO;
import br.com.fiap.entities.Lembrete;

import java.util.List;

public class LembreteService {
    private final LembreteDAO dao;

    public LembreteService(LembreteDAO dao) {
        this.dao = dao;
    }

    public List<LembreteResponseDTO> findAll() {
        return dao.findAllLembrete().stream().map(this::toResponse).toList();
    }

    public LembreteResponseDTO findById(String id) {
        return toResponse(dao.findByIdLembrete(id));
    }

    private LembreteResponseDTO toResponse(Lembrete lembrete) {
        return new LembreteResponseDTO(
            lembrete.getId(),
            lembrete.getDataEnvio(),
            lembrete.getIdConsulta()
        );
    }
}
