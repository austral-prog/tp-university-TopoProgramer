package com.university.cli;

import com.university.cli.entitiesCRUDRepository.ClassroomCRUDRepository;
import com.university.cli.entitiesCRUDRepository.StudentCRUDRepository;
import com.university.cli.entitiesCRUDRepository.TeacherCRUDRepository;
import com.university.cli.entitiesCRUDRepository.SubjectCRUDRepository;
import com.university.cli.actions.CLIExecutor;
import com.university.cli.interfaces.CRUDRepository;

public class Application {
    public void start() {
        // Crear repositorios
        CRUDRepository<?>[] crudRepositories = new CRUDRepository<?>[] {
                new StudentCRUDRepository(),
                new TeacherCRUDRepository(),
                new SubjectCRUDRepository(),
                new ClassroomCRUDRepository()
        };

        // Crear ejecutor del CLI
        CLIExecutor cliExecutor = new CLIExecutor();

        // Ejecutar el CLI
        cliExecutor.runCLI(crudRepositories);
    }
}
