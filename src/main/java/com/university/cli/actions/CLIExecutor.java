package com.university.cli.actions;

import com.university.cli.interfaces.CLI;
import com.university.cli.interfaces.CRUDRepository;
import com.university.cli.interfaces.Entity;
import com.university.cli.operations.CreateOperation;
import com.university.cli.operations.ReadOperation;
import com.university.cli.operations.UpdateOperation;
import com.university.cli.operations.DeleteOperation;

import java.util.Scanner;

public class CLIExecutor implements CLI {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void runCLI(CRUDRepository<?>[] crudInterfaces) {
        while (true) {
            // Mostrar el menú de selección de entidad
            mostrarMenu(crudInterfaces);

            // Leer y validar la opción seleccionada
            int choice = obtenerOpcionMenu(crudInterfaces.length);

            if (choice == 0) {
                break; // Salir si se elige la opción 0
            }

            // Ejecutar operaciones CRUD si la opción es válida
            CRUDRepository<?> crud = crudInterfaces[choice - 1];
            handleCRUDOperations(crud);
        }
    }

    private void mostrarMenu(CRUDRepository<?>[] crudInterfaces) {
        System.out.println("Seleccione una entidad para operar:");
        for (int i = 0; i < crudInterfaces.length; i++) {
            System.out.printf("%d. %s%n", i + 1, crudInterfaces[i].getIdentifier());
        }
        System.out.println("0. Salir");
    }

    private int obtenerOpcionMenu(int maxOption) {
        int choice = -1;
        while (true) {
            System.out.print("Ingrese su opción: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 0 && choice <= maxOption) {
                    break;
                } else {
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
            }
        }
        return choice;
    }

    private <T extends Entity> void handleCRUDOperations(CRUDRepository<T> crud) {
        System.out.println("Operaciones CRUD disponibles:");
        System.out.println("1. Crear\n2. Leer\n3. Actualizar\n4. Eliminar\n0. Volver");

        int operation = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                operation = Integer.parseInt(scanner.nextLine());
                validInput = true; // Se establece en true solo si la entrada es un número válido
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        }

        switch (operation) {
            case 1 -> new CreateOperation<T>(scanner).execute(crud);
            case 2 -> new ReadOperation<T>(scanner).execute(crud);
            case 3 -> new UpdateOperation<T>(scanner).execute(crud);
            case 4 -> new DeleteOperation<T>(scanner).execute(crud);
            case 0 -> System.out.println("Regresando al menú principal...");
            default -> System.out.println("Operación inválida. Por favor, inténtelo de nuevo.");
        }
    }
}
