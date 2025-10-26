package br.com.fiap.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaRequestDTO(
    String especialidade,
    LocalDateTime dataConsulta,
    String status,
    UUID idPaciente
) {}