package com.university.cli.operations;

import com.university.cli.interfaces.CRUDRepository;
import com.university.cli.interfaces.Entity;

import java.util.Scanner;

public class DeleteOperation<T extends Entity> {
    private final Scanner scanner;

    public DeleteOperation(Scanner scanner) {
        this.scanner = scanner;
    }

    public void execute(CRUDRepository<T> crud) {
        System.out.print("Ingrese el ID de la entidad a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        crud.delete(id);
        System.out.println("Entidad eliminada con éxito.");
    }
}
