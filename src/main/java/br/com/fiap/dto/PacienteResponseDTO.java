package br.com.fiap.dto;

import java.util.UUID;

public record PacienteResponseDTO(
    UUID id,
    String nome,
    String email,
    String senha,
    String telefone,
    String acompanhante
) {}