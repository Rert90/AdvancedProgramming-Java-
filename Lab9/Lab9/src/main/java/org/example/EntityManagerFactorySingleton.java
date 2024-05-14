package org.example;

package org.example.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            return Persistence.createEntityManagerFactory("LibraryPU");
        } catch (Throwable ex) {
            // Handle initialization errors
            System.err.println("Initial EntityManagerFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    // Make sure to close EntityManagerFactory when the application shuts down
    public static void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }
}

