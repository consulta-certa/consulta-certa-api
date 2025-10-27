package br.com.fiap.dao;

import br.com.fiap.connections.ConnectionFactory;
import br.com.fiap.entities.Acesso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AcessoDAO {
    private final Connection conn;

    public AcessoDAO() {
        this.conn =  new ConnectionFactory().getConnection();
    }

    public List<Acesso> findAllAcessos() {
        try {
            List<Acesso> acessos = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_acessos_funcionalidade");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Acesso acesso = new Acesso(
                    rs.getString(2),
                    rs.getFloat(3),
                    rs.getFloat(4),
                    rs.getTimestamp(5).toLocalDateTime(),
                    UUID.fromString(rs.getString(6))
                );
                acesso.setId(UUID.fromString(rs.getString(1)));
                acessos.add(acesso);
            }
            stmt.close();
            return acessos;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar acompanhantes", e);
        }
    }

    public Acesso findByIdAcessos(String id) {
        try {
            Acesso acesso = null;
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_acessos_funcionalidade WHERE id=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                acesso = new Acesso(
                    rs.getString(2),
                    rs.getFloat(3),
                    rs.getFloat(4),
                    rs.getTimestamp(5).toLocalDateTime(),
                    UUID.fromString(rs.getString(6))
                );
                acesso.setId(UUID.fromString(rs.getString(1)));
            }
            stmt.close();
            return acesso;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar acompanhante",e);
        }
    }

    public void insertAcessos(Acesso acesso) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cc_acessos_funcionalidade VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, acesso.getId().toString());
            stmt.setString(2, acesso.getFuncionalidade());
            stmt.setFloat(3, acesso.getQuantidadeAcessos());
            stmt.setFloat(4, acesso.getTempoPermanenciaSeg());
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(acesso.getDataAcesso()));
            stmt.setString(6, acesso.getIdPaciente().toString());
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro envolvendo SQL",e);
        }
    }

    public void updateAcessos(Acesso acesso) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE cc_acessos_funcionalidade SET funcionalidade = ?, quantidade_acessos = ?, tempo_permanencia_seg = ?, data_acesso = ?, id_paciente = ? WHERE id = ?");
            stmt.setString(1, acesso.getFuncionalidade());
            stmt.setFloat(2, acesso.getQuantidadeAcessos());
            stmt.setFloat(3, acesso.getTempoPermanenciaSeg());
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(acesso.getDataAcesso()));
            stmt.setString(5, acesso.getIdPaciente().toString());
            stmt.setString(6, acesso.getId().toString());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro envolvendo SQL",e);
        }

    }

    public void deleteAcessos(UUID id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cc_acessos_funcionalidade WHERE id = ?");
            stmt.setString(1, id.toString());
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro envolvendo SQL",e);
        }
    }
}