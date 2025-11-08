package br.com.fiap.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class DadosSaude {
    private UUID id;
    private UUID idPaciente;
    private int idade;
    private String sexo;
    private String temHipertensao;
    private String temDiabetes;
    private String consomeAlcool;
    private String possuiDeficiencia;
    private String tipoDeficiencia;
    private LocalDateTime dataPreenchimento;

    public DadosSaude() {
    }

    public DadosSaude(UUID idPaciente, int idade, String sexo, String temHipertensao, String temDiabetes, String consomeAlcool, String possuiDeficiencia, String tipoDeficiencia, LocalDateTime dataPreenchimento) {
        this.id = UUID.randomUUID();
        this.idPaciente = idPaciente;
        this.idade = idade;
        this.sexo = sexo;
        this.temHipertensao = temHipertensao;
        this.temDiabetes = temDiabetes;
        this.consomeAlcool = consomeAlcool;
        this.possuiDeficiencia = possuiDeficiencia;
        this.tipoDeficiencia = tipoDeficiencia;
        this.dataPreenchimento = dataPreenchimento;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(UUID idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTemHipertensao() {
        return temHipertensao;
    }

    public void setTemHipertensao(String temHipertensao) {
        this.temHipertensao = temHipertensao;
    }

    public String getTemDiabetes() {
        return temDiabetes;
    }

    public void setTemDiabetes(String temDiabetes) {
        this.temDiabetes = temDiabetes;
    }

    public String getConsomeAlcool() {
        return consomeAlcool;
    }

    public void setConsomeAlcool(String consomeAlcool) {
        this.consomeAlcool = consomeAlcool;
    }

    public String getPossuiDeficiencia() {
        return possuiDeficiencia;
    }

    public void setPossuiDeficiencia(String possuiDeficiencia) {
        this.possuiDeficiencia = possuiDeficiencia;
    }

    public String getTipoDeficiencia() {
        return tipoDeficiencia;
    }

    public void setTipoDeficiencia(String tipoDeficiencia) {
        this.tipoDeficiencia = tipoDeficiencia;
    }

    public LocalDateTime getDataPreenchimento() {
        return dataPreenchimento;
    }

    public void setDataPreenchimento(LocalDateTime dataPreenchimento) {
        this.dataPreenchimento = dataPreenchimento;
    }
}