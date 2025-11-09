package br.com.fiap.dao;

import br.com.fiap.connections.ConnectionFactory;
import br.com.fiap.entities.Avaliacao;
import br.com.fiap.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AvaliacaoDAO {
    private final ConnectionFactory factory;

    public AvaliacaoDAO() {
        this.factory =  new ConnectionFactory();
    }

    public List<Avaliacao> findAllAvaliacao() {
        try {
            Connection conn = factory.getConnection();
            List<Avaliacao> avaliacoes = new ArrayList<>();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_avaliacoes");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Avaliacao avaliacao = new Avaliacao(
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getTimestamp(4).toLocalDateTime()
                );
                avaliacao.setId(UUID.fromString(rs.getString(1)));
                avaliacoes.add(avaliacao);
            }
            stmt.close();
            conn.close();
            return avaliacoes;

        } catch (SQLException e) {
            throw new DatabaseException("listar avaliações", e);
        }
    }

    public Avaliacao findByIdAvaliacao(String id) {
        try {
            Connection conn = factory.getConnection();
            Avaliacao avaliacao = null;
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_avaliacoes WHERE id=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                avaliacao = new Avaliacao(
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getTimestamp(4).toLocalDateTime()
                );
                avaliacao.setId(UUID.fromString(rs.getString(1)));
            }
            stmt.close();
            conn.close();
            return avaliacao;

        } catch (SQLException e) {
            throw new DatabaseException("encontrar avaliação", e);
        }
    }

    public void insertAvaliacao(Avaliacao avaliacao) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cc_avaliacoes (id, nota, comentario, data_avaliacao) VALUES (?, ?, ?, ? )");
            stmt.setString(1, avaliacao.getId().toString());
            stmt.setInt(2, avaliacao.getNota());
            stmt.setString(3, avaliacao.getComentario());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(avaliacao.getDataAvaliacao()));
            stmt.execute();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new DatabaseException("registrar avaliação", e);
        }
    }

    public void updateAvaliacao(Avaliacao avaliacao) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE cc_avaliacoes SET nota = ?, comentario = ?, data_avaliacao = ? WHERE id = ?");
            stmt.setInt(1, avaliacao.getNota());
            stmt.setString(2, avaliacao.getComentario());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(avaliacao.getDataAvaliacao()));
            stmt.setString(4, avaliacao.getId().toString());
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("atualizar avaliação", e);
        }
    }

    public void deleteAvaliacao(String id) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cc_avaliacoes WHERE id= ?");
            stmt.setString(1, id);
            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("remover avaliação", e);
        }
    }
}