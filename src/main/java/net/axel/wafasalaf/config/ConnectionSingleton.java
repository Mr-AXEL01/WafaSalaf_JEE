package net.axel.wafasalaf.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionSingleton {
    private static EntityManagerFactory entityManagerFactory ;
    private static EntityManager entityManager ;

    private ConnectionSingleton() {}

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            synchronized (ConnectionSingleton.class) {
                if (entityManagerFactory == null) {
                    entityManagerFactory = Persistence.createEntityManagerFactory("WAFA_SALAF");
                }
            }
        }
        return entityManagerFactory;
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            synchronized (ConnectionSingleton.class) {
                if (entityManager == null || !entityManager.isOpen()) {
                    entityManager = getEntityManagerFactory().createEntityManager();
                }
            }
        }
        return entityManager;
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    public static void closeEntityManager() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
