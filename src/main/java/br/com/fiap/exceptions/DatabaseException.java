package br.com.fiap.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super("|ERRO|: " + message);
    }

    public DatabaseException(String operation, Throwable cause) {
        super(String.format("|ERRO|: não foi possível %s, verificar SQL. ", operation), cause);
    }
}