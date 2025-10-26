package br.com.fiap.dto;

import java.util.UUID;

public record AcompanhanteRequestDTO(
    String nome,
    String email,
    String telefone,
    String parentesco,
    UUID idPaciente
) {}