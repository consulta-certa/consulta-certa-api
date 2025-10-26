package br.com.fiap.dao;

import br.com.fiap.connections.ConnectionFactory;
import br.com.fiap.entities.Acompanhante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AcompanhanteDAO {
    private final Connection conn;

    public AcompanhanteDAO() {
        this.conn =  new ConnectionFactory().getConnection();
    }

    public List<Acompanhante> findAllAcompanhante() {
        try {
            List<Acompanhante> acompanhantes = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_acompanhantes");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Acompanhante acompanhante = new Acompanhante(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(4),
                        UUID.fromString(rs.getString(5))
                );
                acompanhante.setId(UUID.fromString(rs.getString(1)));
                acompanhantes.add(acompanhante);
            }
            stmt.close();
            return acompanhantes;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar acompanhantes", e);
        }
    }

    public Acompanhante findByIdAcompanhante(String id) {
        try {
            Acompanhante acompanhante = null;
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_acompanhantes WHERE id=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                acompanhante = new Acompanhante(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(4),
                        UUID.fromString(rs.getString(5))
                );
                acompanhante.setId(UUID.fromString(rs.getString(1)));
            }
            stmt.close();
            return acompanhante;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar acompanhante", e);
        }
    }

    public void insertAcompanhante(Acompanhante acompanhante) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cc_acompanhantes VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, acompanhante.getId().toString());
            stmt.setString(2, acompanhante.getNome());
            stmt.setString(3, acompanhante.getEmail());
            stmt.setString(4, acompanhante.getTelefone());
            stmt.setString(5, acompanhante.getParentesco());
            stmt.setString(6, acompanhante.getIdPaciente().toString());
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar acompanhante", e);
        }
    }

    public void updateAcompanhante(Acompanhante acompanhante) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE cc_acompanhantes SET nome = ?, email = ?, telefone = ?, parentesco = ?, idPaciente = ? WHERE id = ?");
            stmt.setString(1, acompanhante.getNome());
            stmt.setString(2, acompanhante.getEmail());
            stmt.setString(3, acompanhante.getTelefone());
            stmt.setString(4, acompanhante.getParentesco());
            stmt.setString(5, acompanhante.getIdPaciente().toString());
            stmt.setString(6, acompanhante.getId().toString());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar acompanhante", e);
        }
    }

    public void deleteAcompanhante(String id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cc_acompanhantes WHERE id = ?");
            stmt.setString(1, id);
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover acompanhante", e);
        }
    }
}