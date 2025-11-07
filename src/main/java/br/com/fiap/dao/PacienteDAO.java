package br.com.fiap.dao;

import br.com.fiap.connections.ConnectionFactory;
import br.com.fiap.entities.Paciente;
import br.com.fiap.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PacienteDAO {
    private final ConnectionFactory factory;

    public PacienteDAO() {
        this.factory =  new ConnectionFactory();
    }

    public List<Paciente> findAllPaciente() {
        try {
            Connection conn = factory.getConnection();
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
            conn.close();
            return pacientes;

        } catch (SQLException e) {
            throw new DatabaseException("listar pacientes", e);
        }
    }

    public Paciente findByIdPaciente(String id) {
        try {
            Connection conn = factory.getConnection();
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
            conn.close();
            return paciente;

        } catch (SQLException e) {
            throw new DatabaseException("encontrar paciente", e);
        }
    }

    public void insertPaciente(Paciente paciente) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cc_pacientes VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, paciente.getId().toString());
            stmt.setString(2, paciente.getNome());
            stmt.setString(3, paciente.getEmail());
            stmt.setString(4, paciente.getSenha());
            stmt.setString(5, paciente.getTelefone());
            stmt.setString(6, paciente.getAcompanhantes());
            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("registrar paciente", e);
        }
    }

    public void updatePaciente(Paciente paciente) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE cc_pacientes SET nome = ?, email = ?, senha=?, telefone = ?, acompanhantes = ? WHERE id = ?");
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getEmail());
            stmt.setString(3, paciente.getSenha());
            stmt.setString(4, paciente.getTelefone());
            stmt.setString(5, paciente.getAcompanhantes());
            stmt.setString(6, paciente.getId().toString());
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("atualizar paciente", e);
        }
    }

    public void deletePaciente(String id) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cc_pacientes WHERE id=?");
            stmt.setString(1, id);
            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("remover paciente", e);
        }
    }
}