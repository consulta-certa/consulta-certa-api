package br.com.fiap.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Avaliacao {
    private UUID id;
    private int nota;
    private String comentario;
    private LocalDateTime dataAvaliacao;

    public Avaliacao() {
    }

    public Avaliacao(int nota, String comentario, LocalDateTime dataAvaliacao) {
        this.id = UUID.randomUUID();
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }
}