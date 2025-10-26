package br.com.fiap.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Consulta {
    private UUID id;
    private String especialidade;
    private LocalDateTime dataConsulta;
    private String status;
    private UUID idPaciente;

    public Consulta() {
    }

    public Consulta(String especialidade, LocalDateTime dataConsulta, String status, UUID idPaciente) {
        this.id = UUID.randomUUID();
        this.especialidade = especialidade;
        this.dataConsulta = dataConsulta;
        this.status = status;
        this.idPaciente = idPaciente;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDateTime getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDateTime dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(UUID idPaciente) {
        this.idPaciente = idPaciente;
    }
}