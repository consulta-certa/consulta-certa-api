package br.com.fiap.dao;

import br.com.fiap.entities.Consulta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConsultaDAO {
    private final Connection conn;

    public ConsultaDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Consulta> findAllCosulta() {
        try {
            List<Consulta> consultas = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_consultas");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Consulta consulta = new Consulta(
                    rs.getString(2),
                    rs.getTimestamp(3).toLocalDateTime(),
                    rs.getString(4),
                    UUID.fromString(rs.getString(5))
                );
                consulta.setId(UUID.fromString(rs.getString(1)));
                consultas.add(consulta);
            }
            stmt.close();
            return consultas;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas", e);
        }
    }

    public Consulta findByIdConsulta(String id) {
        try {
            Consulta consulta = null;
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_consultas WHERE id=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                consulta = new Consulta(
                    rs.getString(2),
                    rs.getTimestamp(3).toLocalDateTime(),
                    rs.getString(4),
                    UUID.fromString(rs.getString(5))
                );
                consulta.setId(UUID.fromString(rs.getString(1)));
            }
            stmt.close();
            return consulta;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar consulta", e);
        }
    }

    public void insertConsulta(Consulta consulta) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cc_consultas VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, consulta.getId().toString());
            stmt.setString(2, consulta.getEspecialidade());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(consulta.getDataConsulta()));
            stmt.setString(4, consulta.getStatus());
            stmt.setString(5, consulta.getIdPaciente().toString());
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar consulta", e);
        }
    }

    public void updateConsulta(Consulta consulta) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE cc_consultas SET especialidade = ?, data_consulta = ?, status = ?, id_paciente = ? WHERE id = ?");
            stmt.setString(1, consulta.getEspecialidade());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(consulta.getDataConsulta()));
            stmt.setString(3, consulta.getStatus());
            stmt.setString(4, consulta.getIdPaciente().toString());
            stmt.setString(5, consulta.getId().toString());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar consulta", e);
        }
    }

    public void deleteConsulta(String id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cc_consultas WHERE id = ?");
            stmt.setString(1, id);
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover consulta", e);
        }
    }
}