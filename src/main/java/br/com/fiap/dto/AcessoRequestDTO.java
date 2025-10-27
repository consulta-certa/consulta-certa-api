package br.com.fiap.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AcessoRequestDTO(
    String funcionalidade,
    int quantidadeAcessos,
    float tempoPermanenciaSeg,
    LocalDateTime dataAcesso,
    UUID idPaciente
) {}