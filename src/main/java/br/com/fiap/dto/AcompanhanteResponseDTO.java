package br.com.fiap.dto;

public record AcompanhanteResponseDTO(
    String id,
    String nome,
    String email,
    String telefone,
    String parentesco,
    String idPaciente
) {}