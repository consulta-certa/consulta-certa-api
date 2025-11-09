package br.com.fiap.dao;

import br.com.fiap.connections.ConnectionFactory;
import br.com.fiap.entities.Lembrete;
import br.com.fiap.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LembreteDAO {
    private final ConnectionFactory factory;

    public LembreteDAO() {
        this.factory =  new ConnectionFactory();
    }

    public List<Lembrete> findAllLembrete() {
        try {
            Connection conn = factory.getConnection();
            List<Lembrete> lembretes = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_lembretes");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Lembrete lembrete = new Lembrete(
                    rs.getTimestamp(2).toLocalDateTime(),
                    rs.getString(3),
                    UUID.fromString(rs.getString(4))
                );
                lembrete.setId(UUID.fromString(rs.getString(1)));
                lembretes.add(lembrete);
            }
            stmt.close();
            conn.close();
            return lembretes;

        } catch (SQLException e) {
            throw new DatabaseException("listar lembretes", e);
        }
    }

    public Lembrete findByIdLembrete(String id) {
        try {
            Connection conn = factory.getConnection();
            Lembrete lembrete = null;
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_lembretes WHERE id= ?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                lembrete = new Lembrete(
                    rs.getTimestamp(2).toLocalDateTime(),
                    rs.getString(3),
                    UUID.fromString(rs.getString(4))
                );
                lembrete.setId(UUID.fromString(rs.getString(1)));
            }
            stmt.close();
            conn.close();
            return lembrete;

        } catch (SQLException e) {
            throw new DatabaseException("encontrar lembrete", e);
        }
    }

    public void insertLembrete(Lembrete lembrete) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cc_lembretes (id, data_envio, enviado, d_consulta) VALUES (?, ?, ?, ?)");
            stmt.setString(1, lembrete.getId().toString());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(lembrete.getDataEnvio()));
            stmt.setString(3, lembrete.getEnviado());
            stmt.setString(4, lembrete.getIdConsulta().toString());
            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("registrar lembrete", e);
        }
    }

    public void updateLembrete(Lembrete lembrete) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE cc_lembretes SET data_envio = ?, enviado = ?, id_consulta = ? WHERE id = ?");
            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(lembrete.getDataEnvio()));
            stmt.setString(2, lembrete.getIdConsulta().toString());
            stmt.setString(3, lembrete.getEnviado());
            stmt.setString(4, lembrete.getId().toString());
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("atualizar lembrete", e);
        }
    }

    public void deleteLembrete(String id) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cc_lembretes WHERE id = ?");
            stmt.setString(1, id);
            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("remover lembrete", e);
        }
    }
}