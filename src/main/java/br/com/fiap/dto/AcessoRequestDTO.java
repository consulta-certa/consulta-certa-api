package br.com.fiap.dto;

import br.com.fiap.annotation.NotNullField;

import java.time.LocalDateTime;
import java.util.UUID;

public record AcessoRequestDTO(
    @NotNullField String funcionalidade,
    @NotNullField int quantidadeAcessos,
    @NotNullField int tempoPermanenciaSeg,
    @NotNullField LocalDateTime dataAcesso,
    UUID idPaciente
) {}