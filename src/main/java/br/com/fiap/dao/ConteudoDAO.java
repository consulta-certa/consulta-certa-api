package br.com.fiap.dao;
import br.com.fiap.connections.ConnectionFactory;
import br.com.fiap.entities.Conteudo;
import br.com.fiap.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConteudoDAO {
    private final ConnectionFactory factory;

    public ConteudoDAO() {
        this.factory =  new ConnectionFactory();
    }

    public List<Conteudo> findAllConteudo() {
        try {
            Connection conn = factory.getConnection();
            List<Conteudo> conteudos = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_conteudos");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Conteudo conteudo = new Conteudo(
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getTimestamp(7).toLocalDateTime()
                );
                conteudo.setId(UUID.fromString(rs.getString(1)));
                conteudos.add(conteudo);
            }
            stmt.close();
            conn.close();
            return conteudos;

        } catch (SQLException e) {
            throw new DatabaseException("listar conteudos", e);
        }
    }

    public Conteudo findByIdConteudo(String id) {
        try {
            Connection conn = factory.getConnection();
            Conteudo conteudo = null;
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_conteudos WHERE id=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                conteudo = new Conteudo(
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getTimestamp(7).toLocalDateTime()
                );
                conteudo.setId(UUID.fromString(rs.getString(1)));
            }
            stmt.close();
            conn.close();
            return conteudo;

        } catch (SQLException e) {
            throw new DatabaseException("encontrar conteudo", e);
        }
    }

    public void insertConteudo(Conteudo conteudo) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cc_conteudos (id, tipo, titulo, texto, video, imagem, data_publicacao) VALUES (?, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD\"T\"HH24:MI:SS'))");
            stmt.setString(1, conteudo.getId().toString());
            stmt.setString(2, conteudo.getTipo());
            stmt.setString(3, conteudo.getTitulo());
            stmt.setString(4, conteudo.getTexto());
            stmt.setString(5, conteudo.getVideo());
            stmt.setString(6, conteudo.getImagem());
            stmt.setTimestamp(7, java.sql.Timestamp.valueOf(conteudo.getDataPublicacao()));
            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("inserir conteúdo", e);
        }
    }

    public void updateConteudo(Conteudo conteudo) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE cc_conteudos SET tipo = ?, titulo = ?, texto = ?, video = ?, imagem = ?, data_publicacao = TO_DATE(?, 'YYYY-MM-DD\"T\"HH24:MI:SS') WHERE id = ?");
            stmt.setString(1, conteudo.getTipo());
            stmt.setString(2, conteudo.getTitulo());
            stmt.setString(3, conteudo.getTexto());
            stmt.setString(4, conteudo.getVideo());
            stmt.setString(5, conteudo.getImagem());
            stmt.setTimestamp(6, java.sql.Timestamp.valueOf(conteudo.getDataPublicacao()));
            stmt.setString(7, conteudo.getId().toString());
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("atualizar conteúdo", e);
        }
    }

    public void deleteConteudo(String id) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cc_conteudos WHERE id = ?");
            stmt.setString(1, id);
            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("remover conteúdo", e);
        }
    }
}