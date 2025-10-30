package br.com.fiap.dto;

import br.com.fiap.annotation.NotNullField;

import java.time.LocalDateTime;
import java.util.UUID;

public record InteracaoRequestDTO(
    @NotNullField int idConversa,
    @NotNullField String pergunta,
    @NotNullField LocalDateTime dataPergunta
) {}