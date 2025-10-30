package br.com.fiap.dto;

import br.com.fiap.annotation.NotNullField;

import java.util.UUID;

public record AcompanhanteRequestDTO(
    @NotNullField String nome,
    @NotNullField String email,
    @NotNullField String telefone,
    @NotNullField String parentesco,
    @NotNullField UUID idPaciente
) {}