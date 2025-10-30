package br.com.fiap.dto;

public record ContatoResponseDTO(
    String id,
    String nome,
    String telefone,
    String email,
    String numero,
    String rua,
    String bairro,
    String cidade,
    String cep
) {}