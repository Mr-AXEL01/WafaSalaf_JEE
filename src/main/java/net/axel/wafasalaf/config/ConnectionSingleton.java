package net.axel.wafasalaf.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectionSingleton {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;

    static {
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("WAFA_SALAF");
    }
    public static EntityManagerFactory entityManagerFactory() {
        return ENTITY_MANAGER_FACTORY;
    }

    
}
