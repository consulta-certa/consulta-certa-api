package br.com.fiap.dao;

import br.com.fiap.connections.ConnectionFactory;
import br.com.fiap.entities.Consulta;
import br.com.fiap.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConsultaDAO {
    private final ConnectionFactory factory;

    public ConsultaDAO() {
        this.factory =  new ConnectionFactory();
    }

    public List<Consulta> findAllConsulta() {
        try {
            Connection conn = factory.getConnection();
            List<Consulta> consultas = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_consultas");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Consulta consulta = new Consulta(
                    rs.getString(2),
                    rs.getTimestamp(3).toLocalDateTime(),
                    rs.getString(4),
                    UUID.fromString(rs.getString(5)),
                    rs.getTimestamp(6).toLocalDateTime()

                );
                consulta.setId(UUID.fromString(rs.getString(1)));
                consultas.add(consulta);
            }
            stmt.close();
            conn.close();
            return consultas;

        } catch (SQLException e) {
            throw new DatabaseException("listar consultas", e);
        }
    }

    public Consulta findByIdConsulta(String id) {
        try {
            Connection conn = factory.getConnection();
            Consulta consulta = null;
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_consultas WHERE id=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                consulta = new Consulta(
                    rs.getString(2),
                    rs.getTimestamp(3).toLocalDateTime(),
                    rs.getString(4),
                    UUID.fromString(rs.getString(5)),
                    rs.getTimestamp(6).toLocalDateTime()
                );
                consulta.setId(UUID.fromString(rs.getString(1)));
            }
            stmt.close();
            conn.close();
            return consulta;

        } catch (SQLException e) {
            throw new DatabaseException("encontrar consulta", e);
        }
    }

    public void insertConsulta(Consulta consulta) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cc_consultas (id, data_consulta, especialidade, ativa, id_paciente, data_agendamento) VALUES (?, ?, TO_DATE(?, 'YYYY-MM-DD\"T\"HH24:MI:SS'), ?, ?, TO_DATE(?, 'YYYY-MM-DD\"T\"HH24:MI:SS'))");
            stmt.setString(1, consulta.getId().toString());
            stmt.setString(2, consulta.getEspecialidade());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(consulta.getDataConsulta()));
            stmt.setString(4, consulta.getAtiva());
            stmt.setString(5, consulta.getIdPaciente().toString());
            stmt.setTimestamp(6, java.sql.Timestamp.valueOf(consulta.getDataConsulta()));
            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("registrar consulta", e);
        }
    }

    public void updateConsulta(Consulta consulta) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE cc_consultas SET especialidade = ?, data_consulta = TO_DATE(?, 'YYYY-MM-DD\"T\"HH24:MI:SS'), ativa = ?, id_paciente = ?, data_agendamento = TO_DATE(?, 'YYYY-MM-DD\"T\"HH24:MI:SS') WHERE id = ?");
            stmt.setString(1, consulta.getEspecialidade());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(consulta.getDataConsulta()));
            stmt.setString(3, consulta.getAtiva());
            stmt.setString(4, consulta.getIdPaciente().toString());
            stmt.setString(5, consulta.getId().toString());
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("atualizar consulta", e);
        }
    }

    public void deleteConsulta(String id) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cc_consultas WHERE id = ?");
            stmt.setString(1, id);
            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("remover consulta", e);
        }
    }
}