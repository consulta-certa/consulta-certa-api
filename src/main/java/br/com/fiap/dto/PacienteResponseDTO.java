package br.com.fiap.dto;

public record PacienteResponseDTO(
    String id,
    String nome,
    String email,
    String senha,
    String telefone,
    String acompanhantes
) {}