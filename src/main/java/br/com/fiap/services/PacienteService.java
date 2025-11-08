package br.com.fiap.services;

import br.com.fiap.dao.PacienteDAO;
import br.com.fiap.dto.*;
import br.com.fiap.entities.Paciente;
import br.com.fiap.exceptions.IncorrectPasswordException;
import br.com.fiap.exceptions.EntityNotFoundException;
import br.com.fiap.exceptions.InvalidIdFormatException;
import br.com.fiap.utils.JwtUtils;
import br.com.fiap.utils.PasswordUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static br.com.fiap.utils.PasswordUtils.verifyHash;

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
        String senhaHashed = PasswordUtils.generateHash(request.senha().toCharArray());
        Paciente paciente = new Paciente(
            request.nome(),
            request.email(),
            senhaHashed,
            request.telefone(),
            request.acompanhantes()
        );
        dao.insertPaciente(paciente);

        return toResponse(paciente);
    }

    public TokenResponseDTO login (SignInRequestDTO request) {
        List<Paciente> pacientes = dao.findAllPaciente();
        Optional<Paciente> paciente = pacientes.stream().filter(paciente1 -> paciente1.getEmail().equals(request.email())).findAny();

        if (paciente.isEmpty()) {
            throw new EntityNotFoundException();
        }

        boolean senhaCorreta = verifyHash(request.senha(), paciente.get().getSenha());

        if (!senhaCorreta) {
            throw new IncorrectPasswordException();
        }

        String token = JwtUtils.generateToken(
            paciente.get().getId().toString(),
            paciente.get().getNome(),
            paciente.get().getEmail(),
            paciente.get().getTelefone(),
            paciente.get().getAcompanhantes(),
            paciente.get().getDadosSaude()
        );

        return new TokenResponseDTO(token);
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
            paciente.setDadosSaude(request.dadosSaude());
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
            paciente.getTelefone(),
            paciente.getAcompanhantes(),
            paciente.getDadosSaude()
        );
    }
}
