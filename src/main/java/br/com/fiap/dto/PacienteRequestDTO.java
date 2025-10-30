package br.com.fiap.dto;

import br.com.fiap.annotation.NotNullField;

public record PacienteRequestDTO(
    @NotNullField String nome,
    @NotNullField String email,
    @NotNullField String senha,
    @NotNullField String telefone,
    @NotNullField String acompanhantes
) {}