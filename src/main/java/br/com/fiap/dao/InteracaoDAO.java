package br.com.fiap.dao;

import br.com.fiap.connections.ConnectionFactory;
import br.com.fiap.entities.InteracaoChatbot;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InteracaoDAO {
    private final Connection conn;

    public InteracaoDAO() {
        this.conn =  new ConnectionFactory().getConnection();
    }

    public List<InteracaoChatbot> findAllInteracao() {
        try {
            List<InteracaoChatbot> interacoes = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_interacoes_chatbot");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                InteracaoChatbot interacao = new InteracaoChatbot(
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getTimestamp(4).toLocalDateTime()
                );
                interacao.setId(UUID.fromString(rs.getString(1)));
                interacoes.add(interacao);
            }
            stmt.close();
            return interacoes;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar interações", e);
        }
    }

    public InteracaoChatbot findByIdInteracao(String id) {
        try {
            InteracaoChatbot interacao = null;
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_interacoes_chatbot WHERE id = ?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                interacao = new InteracaoChatbot(
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getTimestamp(4).toLocalDateTime()
                );
                interacao.setId(UUID.fromString(rs.getString(1)));
            }
            stmt.close();
            return interacao;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar interação", e);
        }
    }

    public void insertInteracao(InteracaoChatbot interacao) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cc_interacoes_chatbot VALUES (?, ?, ?, ?)");
            stmt.setString(1, interacao.getId().toString());
            stmt.setInt(2, interacao.getIdConversa());
            stmt.setString(3, interacao.getPergunta());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(interacao.getDataPergunta()));
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar interação", e);
        }
    }

    public void updateInteracao(InteracaoChatbot interacao) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE cc_interacoes_chatbot SET id_conversa = ?, pergunta = ?, data_pergunta = ? WHERE id = ?");
            stmt.setInt(1, interacao.getIdConversa());
            stmt.setString(2, interacao.getPergunta());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(interacao.getDataPergunta()));
            stmt.setString(4, interacao.getId().toString());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar interação", e);
        }
    }

    public void deleteInteracao(String id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cc_interacoes_chatbot WHERE id = ?");
            stmt.setString(1, id);
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover interação", e);
        }
    }
}