package br.com.fiap.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaResponseDTO(
    UUID id,
    String especialidade,
    LocalDateTime dataConsulta,
    String status,
    UUID idPaciente
) {}