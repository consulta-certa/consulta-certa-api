package br.com.fiap.dto;

import java.util.UUID;

public record PacienteRequestDTO(
    String nome,
    String email,
    String senha,
    String telefone,
    String acompanhantes
) {}