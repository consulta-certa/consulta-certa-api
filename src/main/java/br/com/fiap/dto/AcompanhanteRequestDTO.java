package br.com.fiap.dto;

public record AcompanhanteRequestDTO(
    String nome,
    String email,
    String telefone,
    String parentesco,
    String idPaciente
) {}