package br.com.fiap.dto;

import java.time.LocalDateTime;

public record DadosSaudeResponseDTO(
    String id,
    String idPaciente,
    int idade,
    String sexo,
    String temHipertensao,
    String temDiabetes,
    String consomeAlcool,
    String possuiDeficiencia,
    String tipoDeficiencia,
    LocalDateTime dataPreenchimento
) {}
