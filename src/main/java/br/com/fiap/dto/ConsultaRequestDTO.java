package br.com.fiap.dto;

import java.time.LocalDateTime;

public record ConsultaRequestDTO(
    String especialidade,
    LocalDateTime dataConsulta,
    String ativa,
    String idPaciente
) {}