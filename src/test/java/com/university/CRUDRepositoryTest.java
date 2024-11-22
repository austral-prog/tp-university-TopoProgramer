package com.university;

import com.university.cli.interfaces.CRUDRepository;
import com.university.cli.interfaces.Entity;
import com.university.entities.Classroom;
import com.university.entities.Subject;
import com.university.entities.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ServiceLoader;

import static org.junit.jupiter.api.Assertions.*;

class TestEntity implements Entity {
    private int id;
    private String name;

    public TestEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestEntity{id=" + id + ", name='" + name + '\'' + '}';
    }
}

class CRUDRepositoryTest {

    private ServiceLoader<CRUDRepository> serviceLoader;

    @BeforeEach
    void setUp() {
        // Load all available CRUDRepository implementations using ServiceLoader
        serviceLoader = ServiceLoader.load(CRUDRepository.class);
    }

    @Test
    void testServiceLoaderLoadsAllCRUDRepositories() {
        // Ensure that at least one CRUDRepository implementation is found
        boolean found = serviceLoader.iterator().hasNext();
        assertTrue(found, "No CRUDRepository implementations found");
    }

    @Test
    void testCRUDOperationsOnAllRepositories() {
        for (CRUDRepository crudRepository : serviceLoader) {
            assertNotNull(crudRepository, "CRUDRepository should not be null");

            // Print the entity type being tested
            System.out.println("Testing CRUDRepository for entity type: " + crudRepository.getIdentifier());

            // Create an entity
            Entity testEntity = createTestEntity(crudRepository);
            assertNotNull(testEntity, "Created entity should not be null");
            crudRepository.create(testEntity);

            // Read the entity (assuming an ID of 1 for simplicity)
            Object readEntity = crudRepository.read(1);
            assertNotNull(readEntity, "Entity should be found");
            assertEquals(testEntity.toString(), readEntity.toString(), "Read entity should match created entity");

            // Update the entity (assuming an ID of 1)
            Entity updatedEntity = updateTestEntity(crudRepository);
            crudRepository.update(1, updatedEntity);
            Object readUpdatedEntity = crudRepository.read(1);
            assertEquals(updatedEntity.toString(), readUpdatedEntity.toString(), "Updated entity should match");

            // Delete the entity (assuming an ID of 1)
            crudRepository.delete(1);
            Object deletedEntity = crudRepository.read(1);
            assertNull(deletedEntity, "Deleted entity should no longer exist");
        }
    }

    private Entity createTestEntity(CRUDRepository<? extends Entity> crudRepository) {
        try {
            // Obtener la clase de la entidad desde el CRUD repository
            Class<? extends Entity> entityClass = crudRepository.getEntityClass();

            // Usar la reflexión para crear una nueva instancia de la clase de la entidad
            Constructor<? extends Entity> constructor = entityClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            Entity entity = constructor.newInstance();

            // Asignar ID y otros campos necesarios
            entity.setId(1); // Establecer ID

            Field[] fields = entityClass.getDeclaredFields();
            System.out.println(Arrays.toString(fields.clone()));
            for (Field field : fields) {
                field.setAccessible(true); // Permitir acceso a campos privados

                // Asignar datos de ejemplo según el tipo de campo o nombre
                if (field.getType() == String.class) {
                    field.set(entity, "Example " + field.getName()); // Asignar un ejemplo de nombre
                } else if (field.getType() == int.class || field.getType() == Integer.class) {
                    field.set(entity, 1);
                } else if (field.getType() == Subject.class) {
                    field.set(entity, new Subject("Example Subject", new Teacher("Example Teacher"))); // Asignar una nueva instancia de Subject con un Teacher
                } else if (field.getType() == Teacher.class) {
                    field.set(entity, new Teacher("Example Teacher")); // Asignar una nueva instancia de Teacher
                } else if (field.getType() == Classroom.class) {
                    field.set(entity, new Classroom("Example Class", new Subject("Example Subject", new Teacher("Example Teacher")), new Teacher("Example Teacher"), new ArrayList<>())); // Asignar una nueva instancia de Classroom
                }
                // Agregar más verificaciones de tipo según sea necesario
            }

            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Entity updateTestEntity(CRUDRepository<? extends Entity> crudRepository) {
        try {
            // Obtener la clase de la entidad desde el CRUD repository
            Class<? extends Entity> entityClass = crudRepository.getEntityClass();

            // Usar la reflexión para crear una nueva instancia de la clase de la entidad
            Constructor<? extends Entity> constructor = entityClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            Entity entity = constructor.newInstance();

            // Asignar ID y campos de ejemplo actualizados
            entity.setId(1); // Mantener el ID para la actualización

            Field[] fields = entityClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // Permitir acceso a campos privados

                // Asignar datos de ejemplo actualizados según el tipo de campo o nombre
                if (field.getType() == String.class) {
                    field.set(entity, "Updated Example " + field.getName()); // Asignar un nombre de ejemplo actualizado
                } else if (field.getType() == int.class || field.getType() == Integer.class) {
                    field.set(entity, 2); // Asignar un valor diferente para la actualización
                } else if (field.getType() == Subject.class) {
                    field.set(entity, new Subject("Updated Example Subject", new Teacher("Updated Example Teacher"))); // Asignar una nueva instancia de Subject con un Teacher actualizado
                } else if (field.getType() == Teacher.class) {
                    field.set(entity, new Teacher("Updated Example Teacher")); // Asignar una nueva instancia de Teacher
                } else if (field.getType() == Classroom.class) {
                    field.set(entity, new Classroom("Updated Example Class", new Subject("Updated Example Subject", new Teacher("Updated Example Teacher")), new Teacher("Updated Example Teacher"), new ArrayList<>())); // Asignar una nueva instancia de Classroom
                }
                // Agregar más verificaciones de tipo según sea necesario
            }

            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
