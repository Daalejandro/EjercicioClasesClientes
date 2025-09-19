package org.hibernate_jpa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.text.html.parser.Entity;

public class JpaUtil {

    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
            // Aquí va el nombre de la unidad de persistencia que definiste en persistence.xml
            return Persistence.createEntityManagerFactory("ejemploJPA");
    }

    // Retorna un nuevo EntityManager para trabajar con la base de datos
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
