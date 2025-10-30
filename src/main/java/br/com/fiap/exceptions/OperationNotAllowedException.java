package br.com.fiap.exceptions;

public class OperationNotAllowedException extends RuntimeException {
    public OperationNotAllowedException() {
        super("|ERRO|: requisição não permitida para este endpoint.");
    }
}
