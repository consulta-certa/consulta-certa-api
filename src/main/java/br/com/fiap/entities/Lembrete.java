package br.com.fiap.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Lembrete {
    private UUID id;
    private LocalDateTime dataEnvio;
    private UUID idConsulta;

    public Lembrete() {}

    public Lembrete(LocalDateTime dataEnvio, UUID idConsulta) {
        this.id = UUID.randomUUID();
        this.dataEnvio = dataEnvio;
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

    public UUID getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(UUID idConsulta) {
        this.idConsulta = idConsulta;
    }
}