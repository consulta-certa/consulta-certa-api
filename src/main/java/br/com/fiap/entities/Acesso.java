package br.com.fiap.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Acesso {
    private UUID id;
    private String funcionalidade;
    private int quantidadeAcessos;
    private float tempoPermanenciaSeg;
    private LocalDateTime dataAcesso;
    private UUID idPaciente;

    public Acesso() {
    }

    public Acesso(String funcionalidade, int quantidadeAcessos, float tempoPermanenciaSeg, LocalDateTime dataAcesso, UUID idPaciente) {
        this.id = UUID.randomUUID();
        this.funcionalidade = funcionalidade;
        this.quantidadeAcessos = quantidadeAcessos;
        this.tempoPermanenciaSeg = tempoPermanenciaSeg;
        this.dataAcesso = dataAcesso;
        this.idPaciente = idPaciente;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFuncionalidade() {
        return funcionalidade;
    }

    public void setFuncionalidade(String funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    public int getQuantidadeAcessos() {
        return quantidadeAcessos;
    }

    public void setQuantidadeAcessos(int quantidadeAcessos) {
        this.quantidadeAcessos = quantidadeAcessos;
    }

    public float getTempoPermanenciaSeg() {
        return tempoPermanenciaSeg;
    }

    public void setTempoPermanenciaSeg(float tempoPermanenciaSeg) {
        this.tempoPermanenciaSeg = tempoPermanenciaSeg;
    }

    public LocalDateTime getDataAcesso() {
        return dataAcesso;
    }

    public void setDataAcesso(LocalDateTime dataAcesso) {
        this.dataAcesso = dataAcesso;
    }

    public UUID getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(UUID idPaciente) {
        this.idPaciente = idPaciente;
    }
}