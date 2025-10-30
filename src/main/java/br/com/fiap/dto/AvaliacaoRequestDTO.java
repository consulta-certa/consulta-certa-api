package br.com.fiap.dto;

import java.time.LocalDateTime;

public record AvaliacaoRequestDTO(
    int nota,
    String comentario,
    LocalDateTime dataAvaliacao
) {}