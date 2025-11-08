package br.com.fiap.entities;

import java.util.UUID;

public class Paciente {
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String acompanhantes;
    private String dadosSaude;

    public Paciente() {
    }

    public Paciente(String nome, String email, String senha, String telefone, String acompanhantes) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.acompanhantes = acompanhantes;
        this.dadosSaude = "n";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getAcompanhantes() {
        return acompanhantes;
    }

    public void setAcompanhantes(String acompanhantes) {
        this.acompanhantes = acompanhantes;
    }

    public String getDadosSaude() {
        return dadosSaude;
    }

    public void setDadosSaude(String dadosSaude) {
        this.dadosSaude = dadosSaude;
    }
}