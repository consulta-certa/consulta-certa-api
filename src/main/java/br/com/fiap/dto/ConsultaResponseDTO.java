package br.com.fiap.dto;

import java.time.LocalDateTime;

public record ConsultaResponseDTO(
    String id,
    String especialidade,
    LocalDateTime dataConsulta,
    String status,
    String idPaciente
) {}