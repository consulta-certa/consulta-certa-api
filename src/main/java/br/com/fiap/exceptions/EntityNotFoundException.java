package br.com.fiap.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity) {
        super(String.format("|ERRO|: não foi possível encontrar %s com esse id.", entity));
    }
}