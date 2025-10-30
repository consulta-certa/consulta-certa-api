package br.com.fiap.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Lembrete {
    private UUID id;
    private LocalDateTime dataEnvio;
    private String enviado;
    private UUID idConsulta;

    public Lembrete() {}

    public Lembrete(LocalDateTime dataEnvio, String enviado, UUID idConsulta) {
        this.id = UUID.randomUUID();
        this.dataEnvio = dataEnvio;
        this.enviado = enviado;
        this.idConsulta = idConsulta;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getEnviado() {
        return enviado;
    }

    public void setEnviado(String enviado) {
        this.enviado = enviado;
    }

    public UUID getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(UUID idConsulta) {
        this.idConsulta = idConsulta;
    }
}