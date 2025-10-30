package br.com.fiap.dto;

import java.time.LocalDateTime;

public record ConteudoResponseDTO(
    String id,
    String tipo,
    String titulo,
    String texto,
    String video,
    String imagem,
    LocalDateTime dataPublicacao
) {}