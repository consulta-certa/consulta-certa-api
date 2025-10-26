package br.com.fiap.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AvaliacaoRequestDTO(
    int nota,
    String comentario,
    LocalDateTime dataAvaliacao
) {}