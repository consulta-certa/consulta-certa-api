package br.com.fiap.dto;

import java.util.UUID;

public record ContatoResponseDTO(
    UUID id,
    String nome,
    String telefone,
    String email,
    String numero,
    String rua,
    String bairro,
    String cidade,
    String cep
) {}