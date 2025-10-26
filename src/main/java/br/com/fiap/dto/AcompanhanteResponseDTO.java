package br.com.fiap.dto;

import java.util.UUID;

public record AcompanhanteResponseDTO(
    UUID id,
    String nome,
    String email,
    String telefone,
    String parentesco,
    UUID idPaciente
) {}