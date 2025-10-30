package br.com.fiap.exceptions;

import java.util.List;

public class MissingFieldException extends RuntimeException {
    public MissingFieldException(List<String> invalidos) {
        super(
            invalidos.size() > 1 ?
            String.format("|ERRO|: os campos '%s' foram enviados nulo ou fora do formato esperado.", String.join(", ", invalidos)) :
            String.format("|ERRO|: o campo '%s' foi enviado nulo ou fora do formato esperado.", invalidos.toString().replace("[", "").replace("]", ""))
        );
    }
}
