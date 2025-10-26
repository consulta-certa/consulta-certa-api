package br.com.fiap.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConteudoResponseDTO(
    UUID id,
    String tipo,
    String titulo,
    String texto,
    String video,
    String imagem,
    LocalDateTime dataPublicacao
) {}