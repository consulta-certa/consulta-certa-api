package br.com.fiap.exceptions;

public class CredentialsException extends RuntimeException{
    public CredentialsException (String mensagem) {
        super("|ERRO|: " + mensagem);
    }
}
