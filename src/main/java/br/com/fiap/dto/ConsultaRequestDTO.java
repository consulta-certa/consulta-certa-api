package br.com.fiap.dto;

import br.com.fiap.annotation.NotNullField;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultaRequestDTO(
    @NotNullField String especialidade,
    @NotNullField LocalDateTime dataConsulta,
    @NotNullField String status,
    @NotNullField UUID idPaciente
) {}