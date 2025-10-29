package br.com.fiap.dao;

import br.com.fiap.connections.ConnectionFactory;
import br.com.fiap.entities.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PacienteDAO {
    private final Connection conn;

    public PacienteDAO() {
        this.conn =  new ConnectionFactory().getConnection();
    }

    public List<Paciente> findAllPaciente() {
        try {
            List<Paciente> pacientes = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_pacientes");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Paciente paciente = new Paciente(
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
                );
                paciente.setId(UUID.fromString(rs.getString(1)));
                pacientes.add(paciente);
            }
            stmt.close();
            return pacientes;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pacientes", e);
        }
    }

    public Paciente findByIdPaciente(String id) {
        try {
            Paciente paciente = null;
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_pacientes WHERE id =?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                paciente = new Paciente(
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
                );
                paciente.setId(UUID.fromString(rs.getString(1)));
            }
            stmt.close();
            return paciente;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar paciente", e);
        }
    }

    public void insertPaciente(Paciente paciente) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cc_pacientes VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, paciente.getId().toString());
            stmt.setString(2, paciente.getNome());
            stmt.setString(3, paciente.getEmail());
            stmt.setString(4, paciente.getSenha());
            stmt.setString(5, paciente.getTelefone());
            stmt.setString(6, paciente.getAcompanhantes());
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar paciente", e);
        }
    }

    public void updatePaciente(Paciente paciente) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE cc_pacientes SET nome = ?, email = ?, senha=?, telefone = ?, acompanhantes = ? WHERE id = ?");
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getEmail());
            stmt.setString(3, paciente.getSenha());
            stmt.setString(4, paciente.getTelefone());
            stmt.setString(5, paciente.getAcompanhantes());
            stmt.setString(6, paciente.getId().toString());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar paciente", e);
        }
    }

    public void deletePaciente(String id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cc_pacientes WHERE id=?");
            stmt.setString(1, id);
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover paciente", e);
        }
    }
}