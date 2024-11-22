package com.university.tp3.csvActions;

import com.university.tp3.ApprovalCriteria;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvReader_tp3 {

    /**
     * Lee un archivo CSV y crea una lista de objetos ApprovalCriteria.
     *
     * @param inputFilePath la ruta del archivo CSV a leer
     * @return una lista de objetos ApprovalCriteria
     * @throws IOException si ocurre un error de lectura
     */
    public List<ApprovalCriteria> read(String inputFilePath) throws IOException {
        List<ApprovalCriteria> criteriaList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            // Saltar la cabecera del archivo CSV
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Ignorar líneas vacías o malformadas
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] fields = line.split(",");
                // Verificar que haya al menos 4 columnas (los campos mínimos esperados)
                if (fields.length < 4) {
                    System.err.println("Línea malformada o incompleta: " + line);
                    continue; // O puedes lanzar una excepción personalizada si lo prefieres
                }

                String subject = fields[0];         // Nombre de la materia
                String criteriaType = fields[1];    // Tipo de criterio
                double criteriaValue = 0;

                // Validar la conversión de criteriaValue
                try {
                    criteriaValue = Double.parseDouble(fields[2]);
                } catch (NumberFormatException e) {
                    System.err.println("Valor de criterio inválido en la línea: " + line);
                    continue; // Saltar a la siguiente línea si el valor no es numérico
                }

                // Aquí es donde hacemos la corrección: dividimos la columna 4 por comas
                String[] evaluationNamesArray = fields[3].split(",");

                List<String> evaluationNames = new ArrayList<>();
                // Recorremos el array 'fields' desde la posición 3 hasta el final para incluir todos los nombres de evaluación
                for (int i = 3; i < fields.length; i++) {
                    evaluationNames.add(fields[i].trim()); // Añadimos cada evaluación y eliminamos espacios en blanco
                }

                // Crear el objeto ApprovalCriteria con los valores obtenidos
                ApprovalCriteria criteria = new ApprovalCriteria(subject, criteriaType, criteriaValue, evaluationNames);
                criteriaList.add(criteria);
            }
        }
        return criteriaList;
    }
}
