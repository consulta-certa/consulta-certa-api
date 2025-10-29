package br.com.fiap.dao;
import br.com.fiap.connections.ConnectionFactory;
import br.com.fiap.entities.Conteudo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConteudoDAO {
    private final Connection conn;

    public ConteudoDAO() {
        this.conn =  new ConnectionFactory().getConnection();
    }

    public List<Conteudo> findAllConteudo() {
        try {
            List<Conteudo> conteudos = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_conteudos");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Conteudo conteudo = new Conteudo(
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getTimestamp(7).toLocalDateTime()
                );
                conteudo.setId(UUID.fromString(rs.getString(1)));
                conteudos.add(conteudo);
            }
            stmt.close();
            return conteudos;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar conteudos", e);
        }
    }

    public Conteudo findByIdConteudo(String id) {
        try {
            Conteudo conteudo = null;
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_conteudos WHERE id=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                conteudo = new Conteudo(
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getTimestamp(7).toLocalDateTime()
                );
                conteudo.setId(UUID.fromString(rs.getString(1)));
            }
            stmt.close();
            return conteudo;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar conteudo", e);
        }
    }

    public void insertConteudo(Conteudo conteudo) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cc_conteudos VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, conteudo.getId().toString());
            stmt.setString(2, conteudo.getTipo());
            stmt.setString(3, conteudo.getTitulo());
            stmt.setString(4, conteudo.getTexto());
            stmt.setString(5, conteudo.getVideo());
            stmt.setString(6, conteudo.getImagem());
            stmt.setTimestamp(7, java.sql.Timestamp.valueOf(conteudo.getDataPublicacao()));
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir conteúdo", e);
        }
    }

    public void updateConteudo(Conteudo conteudo) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE cc_conteudos SET tipo = ?, titulo = ?, texto = ?, video = ?, imagem = ?, dataPublicacao = ? WHERE id = ?");
            stmt.setString(1, conteudo.getTipo());
            stmt.setString(2, conteudo.getTitulo());
            stmt.setString(3, conteudo.getTexto());
            stmt.setString(4, conteudo.getVideo());
            stmt.setString(5, conteudo.getImagem());
            stmt.setTimestamp(6, java.sql.Timestamp.valueOf(conteudo.getDataPublicacao()));
            stmt.setString(7, conteudo.getId().toString());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar conteúdo", e);
        }
    }

    public void deleteConteudo(String id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cc_conteudos WHERE id = ?");
            stmt.setString(1, id);
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover conteúdo", e);
        }
    }
}