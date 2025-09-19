package org.hibernate_jpa;

import jakarta.persistence.EntityManager;
import org.hibernate_jpa.entity.Cliente;
import org.hibernate_jpa.util.JpaUtil;

import javax.swing.*;

public class HibernateEditar {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            Long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese el id del cliente a modificar:"));
            Cliente c = em.find(Cliente.class, id);

            // Pedir nuevos datos (mostrando los actuales como valor por defecto)
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre:", c.getNombre());
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido:", c.getApellido());
            String pago = JOptionPane.showInputDialog("Ingrese la forma de pago:", c.getFormaPago());

            // Iniciar transacción
            em.getTransaction().begin();

            // Actualizar datos
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setFormaPago(pago);

            // Guardar cambios
            em.merge(c); //Metodo de entityManager sirve para organizar los cambios
            em.getTransaction().commit();

            System.out.println("Cliente actualizado: " + c);

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }

    }

}



