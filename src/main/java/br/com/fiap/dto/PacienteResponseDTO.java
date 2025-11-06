package br.com.fiap.dto;

public record PacienteResponseDTO(
    String id,
    String nome,
    String email,
    String telefone,
    String acompanhantes
) {}