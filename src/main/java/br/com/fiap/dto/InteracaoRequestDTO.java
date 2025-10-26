package br.com.fiap.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record InteracaoRequestDTO(
    int idConversa,
    String pergunta,
    LocalDateTime dataPergunta
) {}