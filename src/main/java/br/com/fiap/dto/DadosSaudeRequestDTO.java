package br.com.fiap.dto;

import java.time.LocalDateTime;

public record DadosSaudeRequestDTO(
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
