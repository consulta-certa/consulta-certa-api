package br.com.fiap.dto;

import java.time.LocalDateTime;

public record LembreteResponseDTO(
    String id,
    LocalDateTime dataEnvio,
    String idConsulta
) {}