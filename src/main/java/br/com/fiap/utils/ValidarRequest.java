package br.com.fiap.utils;

import br.com.fiap.annotation.NotNullField;
import br.com.fiap.exceptions.MissingFieldException;

import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.List;

public class ValidarRequest {
    public static void verificarNulos(Object record) {
        if (record == null ) {
            throw new MissingFieldException(List.of("todos"));
        }

        RecordComponent[] components = record.getClass().getRecordComponents();
        List<String> camposNulos = new ArrayList<>();

        for (RecordComponent component : components) {
            try {
                boolean notNullField = component.isAnnotationPresent(NotNullField.class);
                Object valor = component.getAccessor().invoke(record);

                if (notNullField && valor == null) {
                    camposNulos.add(component.getName());
                }

            } catch (Exception e) {
                throw new RuntimeException("Erro ao acessar campo do record via Reflection", e);
            }
        }

        if (!camposNulos.isEmpty()) {
            throw new MissingFieldException(camposNulos);
        }
    }
}
