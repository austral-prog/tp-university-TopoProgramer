package com.university.cli.operations;

import com.university.cli.interfaces.CRUDRepository;
import com.university.cli.interfaces.Entity;

import java.util.Scanner;

public class ReadOperation<T extends Entity> {
    private final Scanner scanner;

    public ReadOperation(Scanner scanner) {
        this.scanner = scanner;
    }

    public void execute(CRUDRepository<T> crud) {
        System.out.print("Ingrese el ID de la entidad: ");
        int id = Integer.parseInt(scanner.nextLine());
        T entity = crud.read(id);
        if (entity != null) {
            System.out.println("Entidad encontrada: " + entity);
        } else {
            System.out.println("Entidad no encontrada.");
        }
    }
}
