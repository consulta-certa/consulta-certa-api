package br.com.fiap.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class InteracaoChatbot {
    private UUID id;
    private String pergunta;
    private LocalDateTime dataPergunta;

    public InteracaoChatbot() {}

    public InteracaoChatbot(String pergunta, LocalDateTime dataPergunta) {
        this.id = UUID.randomUUID();
        this.pergunta = pergunta;
        this.dataPergunta = dataPergunta;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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