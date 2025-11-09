package br.com.fiap.dao;

import br.com.fiap.connections.ConnectionFactory;
import br.com.fiap.entities.DadosSaude;
import br.com.fiap.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DadosSaudeDAO {
    private final ConnectionFactory factory;

    public DadosSaudeDAO() {
        this.factory = new ConnectionFactory();
    }

    public List<DadosSaude> findAllDadosSaude() {
        try {
            Connection conn = factory.getConnection();
            List<DadosSaude> dadosSaude = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_dados_saude_paciente");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DadosSaude dado = new DadosSaude(
                    UUID.fromString(rs.getString(2)),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getTimestamp(10).toLocalDateTime()
                );
                dado.setId(UUID.fromString(rs.getString(1)));
                dadosSaude.add(dado);
            }
            stmt.close();
            conn.close();
            return dadosSaude;

        } catch (SQLException e) {
            throw new DatabaseException("listar dados de saude", e);
        }
    }

    public DadosSaude findByIdDadosSaude(String id) {
        try {
            Connection conn = factory.getConnection();
            DadosSaude dadosSaude = null;
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_dados_saude_paciente WHERE id=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                dadosSaude = new DadosSaude(
                    UUID.fromString(rs.getString(2)),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getTimestamp(10).toLocalDateTime()
                );
                dadosSaude.setId(UUID.fromString(rs.getString(1)));
            }
            stmt.close();
            conn.close();
            return dadosSaude;

        } catch (SQLException e) {
            throw new DatabaseException("encontrar dados de saude", e);
        }
    }

    public void insertDadosSaude(DadosSaude dadosSaude) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cc_dados_saude_paciente (id, id_paciente, idade, sexo, tem_hipertensao, tem_diabetes, consome_alcool, possui_deficiencia, tipo_deficiencia, data_preenchimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, dadosSaude.getId().toString());
            stmt.setString(2, dadosSaude.getIdPaciente().toString());
            stmt.setInt(3, dadosSaude.getIdade());
            stmt.setString(4, dadosSaude.getSexo());
            stmt.setString(5, dadosSaude.getTemHipertensao());
            stmt.setString(6, dadosSaude.getTemDiabetes());
            stmt.setString(7, dadosSaude.getConsomeAlcool());
            stmt.setString(8, dadosSaude.getPossuiDeficiencia());
            if (dadosSaude.getTipoDeficiencia() != null) {
                stmt.setString(9, dadosSaude.getTipoDeficiencia());
            } else {
                stmt.setNull(9, java.sql.Types.VARCHAR);
            }
            stmt.setTimestamp(10, java.sql.Timestamp.valueOf(dadosSaude.getDataPreenchimento()));
            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("registrar dados de saude", e);
        }
    }

    public void updateDadosSaude(DadosSaude dadosSaude) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE cc_dados_saude_paciente SET id_paciente = ? idade = ?, sexo = ?, tem_hipertensao = ?, tem_diabetes = ?, consome_alcool = ?, possui_deficiencia = ?, tipo_deficiencia = ?, data_preenchimento = ?, id_paciente WHERE id = ?");
            stmt.setInt(1, dadosSaude.getIdade());
            stmt.setString(2, dadosSaude.getSexo());
            stmt.setString(3, dadosSaude.getTemHipertensao());
            stmt.setString(4, dadosSaude.getTemDiabetes());
            stmt.setString(5, dadosSaude.getConsomeAlcool());
            stmt.setString(6, dadosSaude.getPossuiDeficiencia());
            if (dadosSaude.getTipoDeficiencia() != null) {
                stmt.setString(7, dadosSaude.getTipoDeficiencia());
            } else {
                stmt.setNull(7, java.sql.Types.VARCHAR);
            }
            stmt.setTimestamp(8, java.sql.Timestamp.valueOf(dadosSaude.getDataPreenchimento()));
            stmt.setString(9, dadosSaude.getIdPaciente().toString());
            stmt.setString(10, dadosSaude.getId().toString());
            stmt.executeUpdate();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("atualizar dados de saude", e);
        }
    }

    public void deleteDadosSaude(String id) {
        try {
            Connection conn = factory.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cc_dados_saude_paciente WHERE id = ?");
            stmt.setString(1, id);
            stmt.execute();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            throw new DatabaseException("remover dados de saude", e);
        }
    }
}
