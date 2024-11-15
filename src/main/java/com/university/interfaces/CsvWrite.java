package com.university.interfaces;


import java.io.IOException;


public interface CsvWrite<T> {

    // Método para escribir en un archivo CSV
    void write(String filePath, T data) throws IOException;
}
