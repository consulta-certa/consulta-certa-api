package br.com.fiap.services;

import br.com.fiap.dao.DadosSaudeDAO;
import br.com.fiap.dto.DadosSaudeRequestDTO;
import br.com.fiap.dto.DadosSaudeResponseDTO;
import br.com.fiap.entities.DadosSaude;
import br.com.fiap.exceptions.EntityNotFoundException;
import br.com.fiap.exceptions.InvalidIdFormatException;

import java.util.List;
import java.util.UUID;

public class DadosSaudeService {
    private final DadosSaudeDAO dao;

    public DadosSaudeService() {
        this.dao = new DadosSaudeDAO();
    }

    public List<DadosSaudeResponseDTO> findAll() {
        return dao.findAllDadosSaude().stream().map(this::toResponse).toList();
    }

    public DadosSaudeResponseDTO findById(String id) {
        // Regra de negócio: campos id precisam seguir o padrão estabelecido.
        try {
            UUID uuid = UUID.fromString(id);
            DadosSaude dadosSaude = dao.findByIdDadosSaude(uuid.toString());

            if (dadosSaude == null) {
                throw new EntityNotFoundException("dados de saude");
            }
            return toResponse(dadosSaude);

        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }
    }

    public DadosSaudeResponseDTO insert(DadosSaudeRequestDTO request) {
        // Regra de negócio: campos id precisam seguir o padrão estabelecido.
        try {
            UUID uuid = UUID.fromString(request.idPaciente());

        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }

        DadosSaude dadosSaude = new DadosSaude(
            UUID.fromString(request.idPaciente()),
            request.idade(),
            request.sexo(),
            request.temHipertensao(),
            request.temDiabetes(),
            request.consomeAlcool(),
            request.possuiDeficiencia(),
            request.tipoDeficiencia(),
            request.dataPreenchimento()
        );
        dao.insertDadosSaude(dadosSaude);

        return toResponse(dadosSaude);
    }

    public void delete(String id) {
        try {
            if (findById(id) == null) {
                throw new EntityNotFoundException("dados de saude");
            }

            dao.deleteDadosSaude(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidIdFormatException();
        }
    }

    private DadosSaudeResponseDTO toResponse(DadosSaude dadosSaude) {
        return new DadosSaudeResponseDTO(
            dadosSaude.getId().toString(),
            dadosSaude.getIdPaciente().toString(),
            dadosSaude.getIdade(),
            dadosSaude.getSexo(),
            dadosSaude.getTemHipertensao(),
            dadosSaude.getTemDiabetes(),
            dadosSaude.getConsomeAlcool(),
            dadosSaude.getPossuiDeficiencia(),
            dadosSaude.getTipoDeficiencia(),
            dadosSaude.getDataPreenchimento()
        );
    }
}
