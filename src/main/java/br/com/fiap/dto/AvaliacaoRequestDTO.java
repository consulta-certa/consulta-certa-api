package br.com.fiap.dto;

import br.com.fiap.annotation.NotNullField;

import java.time.LocalDateTime;
import java.util.UUID;

public record AvaliacaoRequestDTO(
    @NotNullField int nota,
    String comentario,
    @NotNullField LocalDateTime dataAvaliacao
) {}