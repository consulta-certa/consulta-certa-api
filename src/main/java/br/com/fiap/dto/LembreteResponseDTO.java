package br.com.fiap.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record LembreteResponseDTO(
    UUID id,
    LocalDateTime dataEnvio,
    UUID idConsulta
) {}