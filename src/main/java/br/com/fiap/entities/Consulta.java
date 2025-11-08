package br.com.fiap.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Consulta {
    private UUID id;
    private String especialidade;
    private LocalDateTime dataConsulta;
    private String ativa;
    private UUID idPaciente;
    private LocalDateTime dataAgendamento;

    public Consulta() {
    }

    public Consulta(String especialidade, LocalDateTime dataConsulta, String ativa, UUID idPaciente, LocalDateTime dataAgendamento) {
        this.id = UUID.randomUUID();
        this.especialidade = especialidade;
        this.dataConsulta = dataConsulta;
        this.ativa = ativa;
        this.idPaciente = idPaciente;
        this.dataAgendamento = dataAgendamento;
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

    public String getAtiva() {
        return ativa;
    }

    public void setAtiva(String ativa) {
        this.ativa = ativa;
    }

    public UUID getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(UUID idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDateTime dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
}