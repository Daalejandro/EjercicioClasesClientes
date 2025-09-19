package org.hibernate_jpa;

import jakarta.persistence.EntityManager;
import org.hibernate_jpa.entity.Cliente;
import org.hibernate_jpa.util.JpaUtil;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class HibernateListar {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
        clientes.forEach(System.out::println);
        em.close();

        //Codigo generico
//        EntityManager em = JpaUtil.getEntityManager();
//
//        Cliente cliente = em.find(Cliente.class,1 ); // busca el cliente con id=1
//        System.out.println(cliente);
//
//        em.close();

    }
}
