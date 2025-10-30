package br.com.fiap.dto;

import java.time.LocalDateTime;

public record InteracaoRequestDTO(
    int idConversa,
    String pergunta,
    LocalDateTime dataPergunta
) {}