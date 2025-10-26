package br.com.fiap.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection () {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

//          Futuramente vou atualizar com variáveis de ambiente, por questão de boas práticas de segurança
            return DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                    "RM566315",
                    "050304"
            );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar o driver JDBC", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro de conexão com o banco", e);
        }
    }
}