package br.com.fiap.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Conteudo {
    private UUID id;
    private String tipo;
    private String titulo;
    private String texto;
    private String video;
    private String imagem;
    private LocalDateTime dataPublicacao;

    public Conteudo() {}

    public Conteudo(String tipo, String titulo, String texto, String video, String imagem, LocalDateTime dataPublicacao) {
        this.id = UUID.randomUUID();
        this.tipo = tipo;
        this.titulo = titulo;
        this.texto = texto;
        this.video = video;
        this.imagem = imagem;
        this.dataPublicacao = dataPublicacao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
}