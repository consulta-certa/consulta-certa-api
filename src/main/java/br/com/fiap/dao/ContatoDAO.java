package br.com.fiap.dao;

import br.com.fiap.connections.ConnectionFactory;
import br.com.fiap.entities.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContatoDAO {
    private final Connection conn;

    public ContatoDAO() {
        this.conn =  new ConnectionFactory().getConnection();
    }

    public List<Contato> findAllContato() {
        try {
            List<Contato> contatos = new ArrayList<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_contatos_hc");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato(
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9)
                );
                contato.setId(UUID.fromString(rs.getString(1)));
                contatos.add(contato);
            }
            stmt.close();
            return contatos;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar contatos", e);
        }
    }

    public Contato findByIdContato(String id) {
        try {
            Contato contato = null;
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cc_contatos_hc WHERE id=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                contato = new Contato(
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9)
                );
                contato.setId(UUID.fromString(rs.getString(1)));
            }
            stmt.close();
            return contato;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar contato", e);
        }
    }

    public void insertContato(Contato contato) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO cc_contatos_hc VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, contato.getId().toString());
            stmt.setString(2, contato.getNome());
            stmt.setString(3, contato.getTelefone());
            stmt.setString(4, contato.getEmail());
            stmt.setString(5, contato.getNumero());
            stmt.setString(6, contato.getRua());
            stmt.setString(7, contato.getBairro());
            stmt.setString(8, contato.getCidade());
            stmt.setString(9, contato.getCep());
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar contato", e);
        }
    }

    public void updateContato(Contato contato) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE cc_contatos_hc SET nome = ?, telefone = ?, email = ?, numero = ?, rua = ?, bairro = ?, cidade = ?, cep = ? WHERE id_contato = ?");
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());
            stmt.setString(4, contato.getNumero());
            stmt.setString(5, contato.getRua());
            stmt.setString(6, contato.getBairro());
            stmt.setString(7, contato.getCidade());
            stmt.setString(8, contato.getCep());
            stmt.setString(9, contato.getId().toString());
            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar contato", e);
        }
    }

    public void deleteContato(String id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cc_contatos_hc WHERE id_contato = ?");
            stmt.setString(1, id);
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover contato", e);
        }
    }
}
