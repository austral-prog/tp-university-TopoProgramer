package com.university.interfaces;

import java.io.IOException;

public interface CsvRead<T> {
    /**
     * Lee datos desde un archivo CSV y los convierte en una estructura de tipo T.
     *
     * @param filePath la ruta del archivo CSV a leer
     * @throws IOException si ocurre un error de lectura
     */
    void read(String filePath) throws IOException;
}
