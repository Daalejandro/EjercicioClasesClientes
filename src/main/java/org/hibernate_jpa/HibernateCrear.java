package org.hibernate_jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManager;
import org.hibernate_jpa.entity.Cliente;
import org.hibernate_jpa.util.JpaUtil;

import javax.swing.*;



public class HibernateCrear {
    public static void main(String[] args) {


        EntityManager em = JpaUtil.getEntityManager();//Iniciamos conexion
        try{

            String nombre = JOptionPane.showInputDialog("Ingrese el nombre");
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido");
            String pago = JOptionPane.showInputDialog("Ingrese la forma de pago");

            em.getTransaction().begin();
            Cliente client = new Cliente();
            client.setNombre(nombre);
            client.setApellido(apellido);
            client.setFormaPago(pago);
            em.persist(client); //Tenelo listo
            em.getTransaction().commit();// transaccion

            System.out.println("El id del cliente registrado es " + client.getId());
            client = em.find(Cliente.class, client.getId());
            System.out.println(client);


        }catch(Exception e){
            em.getTransaction().rollback(); // Ponerlos por que si da error al insertar esto nos ayudara
            e.printStackTrace(); // A que no se cuelgue ek program y se vuelva a dejar la base de datos
                                // Tal cual como estaba
        }finally{
            em.close();
        }


    }
}
