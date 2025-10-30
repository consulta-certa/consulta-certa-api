package br.com.fiap.dto;

import java.time.LocalDateTime;

public record AcessoRequestDTO(
    String funcionalidade,
    int quantidadeAcessos,
    int tempoPermanenciaSeg,
    LocalDateTime dataAcesso,
    String idPaciente
) {}