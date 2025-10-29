package br.com.fiap.dto;

public record PacienteRequestDTO(
    String nome,
    String email,
    String senha,
    String telefone,
    String acompanhantes
) {}