package br.com.fiap.entities;

import java.util.UUID;

public class Acompanhante {
    private UUID id;
    private String nome;
    private String email;
    private String telefone;
    private String parentesco;
    private UUID idPaciente;

    public Acompanhante() {
    }

    public Acompanhante(String nome, String email, String telefone, String parentesco, UUID idPaciente) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.parentesco = parentesco;
        this.idPaciente = idPaciente;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public UUID getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(UUID idPaciente) {
        this.idPaciente = idPaciente;
    }
}