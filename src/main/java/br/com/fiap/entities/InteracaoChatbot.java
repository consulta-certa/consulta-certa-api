package br.com.fiap.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class InteracaoChatbot {
    private UUID id;
    private int idConversa;
    private String pergunta;
    private LocalDateTime dataPergunta;

    public InteracaoChatbot() {}

    public InteracaoChatbot(int idConversa, String pergunta, LocalDateTime dataPergunta) {
        this.id = UUID.randomUUID();
        this.idConversa = idConversa;
        this.pergunta = pergunta;
        this.dataPergunta = dataPergunta;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getIdConversa() {
        return idConversa;
    }

    public void setIdConversa(int idConversa) {
        this.idConversa = idConversa;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public LocalDateTime getDataPergunta() {
        return dataPergunta;
    }

    public void setDataPergunta(LocalDateTime dataPergunta) {
        this.dataPergunta = dataPergunta;
    }
}