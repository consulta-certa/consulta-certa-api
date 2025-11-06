package br.com.fiap.exceptions;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException() {
        super("|ERRO|: senha incorreta.");
    }
}
